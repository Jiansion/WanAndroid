package com.conch.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeNetLife
import com.conch.wanandroid.base.BaseResponse
import com.conch.wanandroid.model.UserModel
import com.conch.wanandroid.repository.LoginAndRegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author YeJain
 * @date 2022/8/9 11:49
 */
class LoginAndRegisterViewModel : ViewModel() {
    private val repository: LoginAndRegisterRepository = LoginAndRegisterRepository()

    private val _loginResult = MutableStateFlow<BaseResponse<UserModel>>(BaseResponse())
    val loginResult: StateFlow<BaseResponse<UserModel>> = _loginResult

    fun login(account: String, password: String) {
        scopeNetLife {
            _loginResult.value = repository.login(account, password)
        }
    }
}