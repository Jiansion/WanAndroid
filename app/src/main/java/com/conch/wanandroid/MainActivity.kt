package com.conch.wanandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conch.wanandroid.databinding.ActivityMainBinding
import com.conch.wanandroid.ui.index.IndexFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, IndexFragment())
            .commit()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}