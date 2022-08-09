package com.conch.wanandroid.constants

/**
 * @author YeJain
 * @date 2022/8/5 14:20
 */
object Api {

    const val BASE_HOST = "https://www.wanandroid.com/"

    /**
     * 登录
     */
    const val LOGIN_URL = "user/login"

    /**
     * 注册
     */
    const val REGISTER_URL = "user/register"


    /**
     * 退出登录
     */
    const val LOGOUT_URL = "user/logout/json"


    /**
     * 获取首页列表数据
     * @param index 页码，从 0 开始
     */
    fun indexDataUrl(index: Int = 0) = "article/list/$index/json"


}