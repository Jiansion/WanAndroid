package com.conch.wanandroid

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun textFlow() {
        runBlocking {
            makeFlow().collect { data ->
                println("receive $data")
            }
            println("run end")
        }
    }


    private fun makeFlow() = flow {
        println("send 1")
        emit(1)
        println("send 2")
        emit(2)
        println("send 2 again")
        emit(2)
        println("send 3")
        emit(3)
        println("发送结束")
    }
}