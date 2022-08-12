package com.conch.wanandroid.ui.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.conch.wanandroid.databinding.ActivityContentWebBinding
import com.conch.wanandroid.utils.WebViewPool
import com.conch.wanandroid.widget.BaseWebView

/**
 * @author YeJain
 * @date 2022/8/12 15:14
 */
class ContentWebActivity : AppCompatActivity() {
    companion object {
        private const val KEY_STRING_TITLE = "key_string_title"
        private const val KEY_STRING_URL = "key_string_url"
        fun startInstance(context: Context, title: String, url: String) {
            val intent = Intent(context, ContentWebActivity::class.java).apply {
                putExtra(KEY_STRING_TITLE, title)
                putExtra(KEY_STRING_URL, url)
            }
            context.startActivity(intent)
        }
    }


    private val binding: ActivityContentWebBinding by lazy {
        ActivityContentWebBinding.inflate(layoutInflater)
    }
    private lateinit var webView: BaseWebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        webView = WebViewPool.obtain(this)
        webView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        binding.fl.addView(webView)


        val url = intent?.getStringExtra(KEY_STRING_URL) ?: ""
        webView.loadUrl(url)
    }


    override fun onDestroy() {
        super.onDestroy()
        WebViewPool.recycle(webView)
    }
}