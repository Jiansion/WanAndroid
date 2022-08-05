package com.conch.wanandroid

import android.app.Application
import android.view.View
import com.conch.wanandroid.constants.Api
import com.conch.wanandroid.utils.log.LogCat
import com.conch.wanandroid.utils.net.MoshiConvert
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.interfaces.NetErrorHandler
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setErrorHandler
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.net.request.BaseRequest
import com.hjq.toast.ToastUtils
import java.util.concurrent.TimeUnit

/**
 * @author YeJain
 * @date 2022/8/5 14:18
 */
class WanAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLib()
    }

    private fun initLib() {
        LogCat.setDebug(BuildConfig.DEBUG, "wanandroid")
        initToast()
        initNet()
    }

    // 网络初始化
    private fun initNet() {
        NetConfig.initialize(host = Api.BASE_HOST) {
            connectTimeout(2, TimeUnit.MINUTES)
            readTimeout(2, TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES)
            setDebug(BuildConfig.DEBUG)
            setConverter(MoshiConvert())
            addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))  // 日志打印拦截器

            setRequestInterceptor(object : RequestInterceptor {
                override fun interceptor(request: BaseRequest) {
                    // 添加通用请求头 等操作
                }
            })

            setErrorHandler(object : NetErrorHandler {
                // 网络请求抛出的异常
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                // 使用缺省页作用域时抛出的的异常
                override fun onStateError(e: Throwable, view: View) {
                    e.printStackTrace()
                }
            })
        }
    }

    // 初始化 Toast
    private fun initToast() {
        ToastUtils.init(this)
        ToastUtils.setDebugMode(BuildConfig.DEBUG)
    }

}