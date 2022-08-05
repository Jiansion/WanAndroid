package com.conch.wanandroid.base

import com.squareup.moshi.JsonClass

/**
 * @author YeJain
 * @date 2022/8/5 14:50
 */
@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val errorCode: Int = 0,
    val errorMsg: String = "",
    val data: T? = null
)