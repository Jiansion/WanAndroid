package com.conch.wanandroid.utils.net

import com.drake.net.convert.NetConverter
import com.drake.net.exception.ConvertException
import com.drake.net.exception.RequestParamsException
import com.drake.net.exception.ServerResponseException
import com.drake.net.response.file
import com.squareup.moshi.Moshi
import okhttp3.Response
import okio.ByteString
import java.io.File
import java.lang.reflect.GenericArrayType
import java.lang.reflect.Type

/**
 * @author YeJain
 * @date 2022/8/5 14:48
 */
class MoshiConvert : NetConverter {
    private val moshi = Moshi.Builder().build()
    override fun <R> onConvert(succeed: Type, response: Response): R? {
        return when {
            succeed === ByteString::class.java && response.isSuccessful -> response.body?.byteString() as R?
            succeed is GenericArrayType && succeed.genericComponentType === Byte::class.java && response.isSuccessful -> response.body?.bytes() as R?
            succeed === File::class.java && response.isSuccessful -> response.file() as R?
            succeed === Response::class.java -> response as R?
            else -> {
                val code = response.code
                when {
                    code in 200..299 -> {
                        val bodyString = response.body?.string() ?: return null
                        return convertResponse(succeed, bodyString)
                    }
                    code in 400..499 -> throw RequestParamsException(
                        response,
                        code.toString()
                    )
                    code >= 500 -> throw ServerResponseException(
                        response,
                        code.toString()
                    )
                    else -> throw ConvertException(response)
                }
            }
        }
    }

    // 对数据进行转换操作
    private fun <R> convertResponse(succeed: Type, body: String): R? {
        return if (succeed === String::class.java) {
            body as R?
        } else {
            moshi.adapter<R>(succeed).fromJson(body)
        }
    }

}