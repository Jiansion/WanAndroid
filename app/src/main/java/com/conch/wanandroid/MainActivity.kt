package com.conch.wanandroid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.conch.wanandroid.databinding.ActivityMainBinding
import com.conch.wanandroid.utils.log.LogCat
import com.conch.wanandroid.viewmodel.LoginAndRegisterViewModel
import com.hjq.toast.ToastUtils
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val loginAndRegisterViewModel by viewModels<LoginAndRegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLog.setOnClickListener {
            LogCat.i("登录请求")
            loginAndRegisterViewModel.login("dasi_ye", "dasi_ye_")
        }

        binding.btnToast.setOnClickListener {
            ToastUtils.show("hahahahah")
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginAndRegisterViewModel.loginResult.collect { data ->
                    LogCat.i(data)
                }
            }
        }

    }
}