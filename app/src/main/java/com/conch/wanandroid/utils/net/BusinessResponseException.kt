package com.conch.wanandroid.utils.net

import com.drake.net.exception.HttpResponseException
import okhttp3.Response

/**
 * @author YeJain
 * @date 2022/8/9 14:07
 * 未知响应错误
 */
class BusinessResponseException(
    response: Response,
    message: String? = null,
    cause: Throwable? = null,
) : HttpResponseException(response, message, cause)