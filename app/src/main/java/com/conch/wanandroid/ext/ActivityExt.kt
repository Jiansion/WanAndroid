package com.conch.wanandroid.ext

import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * 设置状态栏和导航栏的提示文本为深色
 * @param isDark true:深色   false:浅色
 */
fun ComponentActivity.setBarTextDark(isDark: Boolean) {
    WindowCompat.getInsetsController(window, window.decorView).run {
        isAppearanceLightNavigationBars = isDark
        isAppearanceLightStatusBars = isDark
    }
}

fun ComponentActivity.hideSystemBar() {
    WindowCompat.getInsetsController(window, window.decorView).run {
        hide(WindowInsets.Type.systemBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}