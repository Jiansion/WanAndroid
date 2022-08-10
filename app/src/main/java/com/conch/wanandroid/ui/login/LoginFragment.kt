package com.conch.wanandroid.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.conch.wanandroid.base.viewBinding
import com.conch.wanandroid.databinding.FragmentLoginBinding
import com.conch.wanandroid.viewmodel.LoginAndRegisterViewModel
import com.hjq.toast.ToastUtils
import kotlinx.coroutines.launch

/**
 * @author YeJain
 * @date 2022/8/9 16:32
 */
class LoginFragment : Fragment() {


    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    private val loginViewModel: LoginAndRegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnLogin.setOnClickListener { login() }

        lifecycleScope.launch {
            loginViewModel.loginResult.collect { data ->

            }
        }


        return binding.root
    }


    private fun login() {
        val account = binding.etAccount.text.toString()
        val password = binding.etPassword.text.toString()

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            ToastUtils.show("账号/密码不能为空")
            return
        }
        loginViewModel.login(account, password)

    }


}