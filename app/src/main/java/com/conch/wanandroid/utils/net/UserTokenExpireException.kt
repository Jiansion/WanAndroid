package com.conch.wanandroid.utils.net

import com.drake.net.exception.HttpResponseException
import okhttp3.Response

/**
 * 网关Token 过期异常
 * @author YeJain
 * @date 2022/3/29 11:30 上午
 *
 */
class UserTokenExpireException(
    response: Response,
    message: String? = null,
    cause: Throwable? = null,
) : HttpResponseException(response, message, cause)