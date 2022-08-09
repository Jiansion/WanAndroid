package com.conch.wanandroid.repository

import com.conch.wanandroid.base.BaseResponse
import com.conch.wanandroid.constants.Api
import com.conch.wanandroid.model.UserModel
import com.drake.net.Get
import com.drake.net.Post
import kotlinx.coroutines.CoroutineScope

/**
 * @author YeJain
 * @date 2022/8/8 11:18
 */
class LoginAndRegisterRepository(private val scope: CoroutineScope) {


    /**
     * 进行登录操作，登录成功后持久化缓冲 cookie,和缓冲当前用户信息
     * @param username 账号
     * @param password 密码
     */
    suspend fun login(username: String, password: String): BaseResponse<UserModel> {
        return scope.Post<BaseResponse<UserModel>>(Api.LOGIN_URL) {
            param("username", username)
            param("password", password)
        }.await()
    }

    /**
     * 退出登录,退出成功后 清除 cookie,和当前用户数据
     */
    suspend fun logout() {
        scope.Get<BaseResponse<String>>(Api.LOGOUT_URL).await()
    }

    /**
     * 用户注册操作，注册成功后缓冲当前用户数据 和 cookie
     * @param username 账号
     * @param password 密码
     * @param repassword 重试密码
     */
    suspend fun register(username: String, password: String, repassword: String = password) {
        scope.Post<BaseResponse<UserModel>>(Api.REGISTER_URL) {
            param("username", username)
            param("password", password)
            param("repassword", repassword)
        }.await()
    }


}