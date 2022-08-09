package com.conch.wanandroid.ext

import android.content.res.Resources
import android.util.TypedValue

/**
 * 数值相关拓展属性/方法
 */

// dp 转 px
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
val Int.dp
    get() = this.toFloat().dp.toInt()


// sp 转 px
val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
val Int.sp
    get() = this.toFloat().sp.toInt()