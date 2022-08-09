package com.conch.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeNetLife
import androidx.lifecycle.viewModelScope
import com.conch.wanandroid.repository.LoginAndRegisterRepository

/**
 * @author YeJain
 * @date 2022/8/9 11:49
 */
class LoginAndRegisterViewModel : ViewModel() {
    private val repository: LoginAndRegisterRepository = LoginAndRegisterRepository(viewModelScope)
    fun login(account: String, password: String) {
        scopeNetLife {
            repository.login(account, password)
        }
    }
}