package com.conch.wanandroid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.conch.wanandroid.databinding.ActivityMainBinding
import com.conch.wanandroid.utils.log.LogCat
import com.conch.wanandroid.viewmodel.LoginAndRegisterViewModel
import com.hjq.toast.ToastUtils

class MainActivity : AppCompatActivity() {

    private val loginAndRegisterViewModel by viewModels<LoginAndRegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLog.setOnClickListener {
            LogCat.i("登录请求")
            loginAndRegisterViewModel.login("dasi_ye","dasi_ye_")
        }

        binding.btnToast.setOnClickListener {
            ToastUtils.show("hahahahah")
        }
    }
}