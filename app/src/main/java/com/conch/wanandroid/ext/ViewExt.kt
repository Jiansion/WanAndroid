package com.conch.wanandroid.ext

import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.*

/**
 * 在View中执行延时性操作
 * 替换 postDelayed
 */
fun View.delayOnLifecycle(
    delayMillis: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job? = findViewTreeLifecycleOwner()?.let {
    it.lifecycle.coroutineScope.launch(dispatcher) {
        delay(delayMillis)
        block.invoke()
    }
}