package com.conch.wanandroid

import android.app.Application
import android.view.View
import com.conch.wanandroid.constants.Api
import com.conch.wanandroid.utils.WebViewPool
import com.conch.wanandroid.utils.log.LogCat
import com.conch.wanandroid.utils.net.LoginCookieJar
import com.conch.wanandroid.utils.net.MoshiConvert
import com.conch.wanandroid.utils.net.UnknownResponseException
import com.conch.wanandroid.utils.net.UserTokenExpireException
import com.drake.brv.PageRefreshLayout
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.interfaces.NetErrorHandler
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setErrorHandler
import com.drake.net.okhttp.trustSSLCertificate
import com.hjq.toast.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import java.util.concurrent.TimeUnit

/**
 * @author YeJain
 * @date 2022/8/5 14:18
 */
class WanAndroidApp : Application() {

    companion object {
        lateinit var INSTANCE: WanAndroidApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        LogCat.i("WanAndroidApp onCreate")
    }

}