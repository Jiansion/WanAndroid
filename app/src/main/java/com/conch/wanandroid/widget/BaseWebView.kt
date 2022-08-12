package com.conch.wanandroid.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView
import com.conch.wanandroid.BuildConfig

/**
 * @author YeJain
 * @date 2022/8/12 15:01
 */
class BaseWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : WebView(context, attrs, defStyle) {

    init {
        // 开发阶段开启调试模式
        setWebContentsDebuggingEnabled(BuildConfig.DEBUG)

        isScrollContainer = false
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        background = ColorDrawable(Color.TRANSPARENT)
        background.alpha = 0
        setBackgroundColor(0)

        settings.run {
            javaScriptEnabled = true
            //是否支持DOM LocalhostStorage
            domStorageEnabled = true
            // 是否支持数据库
            databaseEnabled = true
            //是否支持文件访问
            allowFileAccess = true
            //是否将视图调整到合适大小
            useWideViewPort = false
            // 是否适配屏幕
            loadWithOverviewMode = true
            //是否使用内置的缩放
            builtInZoomControls = false

            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        }
    }


}