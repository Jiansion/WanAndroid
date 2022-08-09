package com.conch.wanandroid.ext

import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat

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