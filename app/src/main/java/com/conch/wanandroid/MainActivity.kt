package com.conch.wanandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.conch.wanandroid.databinding.ActivityMainBinding
import com.conch.wanandroid.utils.log.LogCat
import com.hjq.toast.ToastUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLog.setOnClickListener {
            LogCat.i("你好哈哈哈哈哈")
            Log.e("TAG", "onCreate: ")
        }

        binding.btnToast.setOnClickListener {
            ToastUtils.show("hahahahah")
        }
    }
}