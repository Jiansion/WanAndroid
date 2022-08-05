package com.conch.wanandroid.constants

/**
 * @author YeJain
 * @date 2022/8/5 14:20
 */
object Api {

    const val BASE_HOST = "https://www.wanandroid.com/"

    /**
     * 获取首页列表数据
     * @param index 页码，从 0 开始
     */
    fun indexDataUrl(index: Int = 0) = "article/list/$index/json"


}