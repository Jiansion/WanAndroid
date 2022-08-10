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
    val data: T? = null,
    // 忽略该字段的JSON序列化
    @Transient val requestState: RequestState = RequestState.LOADING
)

enum class RequestState {
    /**
     * 发送请求
     */
    LOADING,

    /**
     * 请求成功
     */
    SUCCESS,

    /**
     * 请求失败
     */
    ERROR
}