package com.conch.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conch.wanandroid.databinding.ActivityLoginAndRegisterBinding

class LoginAndRegisterActivity : AppCompatActivity() {

    private val binding: ActivityLoginAndRegisterBinding by lazy {
        ActivityLoginAndRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}