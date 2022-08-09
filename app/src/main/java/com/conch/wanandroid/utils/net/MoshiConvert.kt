package com.conch.wanandroid.utils.net

import com.conch.wanandroid.constants.ResponseCodeConstants
import com.drake.net.convert.NetConverter
import com.drake.net.exception.ConvertException
import com.drake.net.exception.RequestParamsException
import com.drake.net.exception.ServerResponseException
import com.drake.net.response.file
import com.squareup.moshi.Moshi
import okhttp3.Response
import okio.ByteString
import org.json.JSONObject
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
                        val codeAndMsg = getResponseCodeAndMsg(bodyString)
                        when (codeAndMsg.first) {
                            ResponseCodeConstants.CODE_SUCCESS -> {
                                return convertResponse(succeed, bodyString)
                            }

                            // 登录信息过期
                            ResponseCodeConstants.CODE_USER_TOKEN_EXPIRE -> {
                                throw UserTokenExpireException(response, "登录信息已过期")
                            }

                            // 未知响应结果
                            ResponseCodeConstants.CODE_UNKNOWN -> {
                                throw UnknownResponseException(response, codeAndMsg.second)
                            }

                            // 请求结果业务异常
                            else -> {
                                throw BusinessResponseException(response, codeAndMsg.second)
                            }
                        }
                    }
                    code in 400..499 -> {
                        throw RequestParamsException(response, code.toString())
                    }

                    code >= 500 -> {
                        throw ServerResponseException(response, code.toString())
                    }
                    else -> {
                        throw ConvertException(response)
                    }
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

    private fun getResponseCodeAndMsg(bodyString: String): Pair<Int, String> {
        return try {
            val json = JSONObject(bodyString)
            val msg = json.getString("errorMsg")
            val code = json.getInt("errorCode")
            Pair(code, msg)
        } catch (e: Exception) {
            Pair(ResponseCodeConstants.CODE_UNKNOWN, "未知响应结果")
        }
    }
}