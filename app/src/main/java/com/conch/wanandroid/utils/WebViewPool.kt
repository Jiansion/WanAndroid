package com.conch.wanandroid.utils

import android.content.Context
import android.content.MutableContextWrapper
import android.os.Looper
import android.view.ViewGroup
import com.conch.wanandroid.utils.log.LogCat
import com.conch.wanandroid.widget.BaseWebView

/**
 * BaseWebView 缓存池
 * @author YeJain
 * @date 2022/8/12 14:21
 */
object WebViewPool {

    private const val MAX_SIZE = 3

    private val webViewCache: MutableList<BaseWebView> = ArrayList()

    private fun create(context: Context): BaseWebView {
        return BaseWebView(context)
    }


    /**
     * 初始化BaseWebView，已使用 IdleHandler 会在空闲的时候初始化BaseWebView
     */
    fun prepare(context: Context) {
        if (webViewCache.isEmpty()) {
            Looper.myQueue().addIdleHandler {
                webViewCache.add(create(MutableContextWrapper(context)))
                false
            }
        }
    }

    /**
     * 获取一个BaseWebView
     */
    fun obtain(context: Context): BaseWebView {
        if (webViewCache.isEmpty()) {
            webViewCache.add(create(MutableContextWrapper(context)))
        }
        val webView = webViewCache.removeFirst()
        (webView.context as MutableContextWrapper).baseContext = context
        return webView.apply {
            clearHistory()
            resumeTimers()
        }
    }

    /**
     * 回收当前的BaseWebView
     * @param webView
     */
    fun recycle(webView: BaseWebView) {
        try {
            webView.run {
                stopLoading()
                loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
                clearHistory()
                pauseTimers()
                clearFormData()
                parent?.let {
                    (it as ViewGroup).removeView(webView)
                }
            }
        } catch (e: Exception) {
            LogCat.d("回收 BaseWebView 失败")
            e.printStackTrace()
        } finally {
            if (webViewCache.size < MAX_SIZE) {
                webViewCache.add(webView)
            }
        }
    }

    /**
     * 销毁资源
     */
    fun destroy() {
        try {
            webViewCache.forEach { webView ->
                webView.removeAllViews()
                webView.destroy()
            }
            webViewCache.clear()
        } catch (e: Exception) {
            LogCat.e("销毁缓存中的 BaseWebView 失败")
            e.printStackTrace()
        }
    }
}