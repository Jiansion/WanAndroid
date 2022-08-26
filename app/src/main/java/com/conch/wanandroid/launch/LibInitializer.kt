package com.conch.wanandroid.launch

import android.app.Application
import android.content.Context
import android.view.View
import androidx.startup.Initializer
import com.conch.wanandroid.BuildConfig
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
 * 初始化一些第三方库
 * https://mp.weixin.qq.com/s/rverE0OGRnncB5-K-_Wesg
 * @author YeJain
 * @date 2022/8/26 09:45
 */
class LibInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LogCat.setDebug(BuildConfig.DEBUG, "wanandroid")
        initToast(context)
        initRefreshLayout(context)
        WebViewPool.prepare(context)
        initNet(context)
        LogCat.i("LibInitializer create")
    }

    // 用于返回需要依赖的气态 Initializer ，默认返回空列表即可
    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }


    // 网络初始化
    private fun initNet(context: Context) {
        NetConfig.initialize(host = Api.BASE_HOST) {
            connectTimeout(2, TimeUnit.MINUTES)
            readTimeout(2, TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES)
            cookieJar(LoginCookieJar(context)) // 添加Cookie
            setDebug(BuildConfig.DEBUG)
            setConverter(MoshiConvert())
            trustSSLCertificate()
            addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))  // 日志打印拦截器
            setErrorHandler(object : NetErrorHandler {
                // 网络请求抛出的异常
                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    if (e is UnknownResponseException) {
                        ToastUtils.show(e.message)
                        return
                    }

                    if (e is UserTokenExpireException) {
                        // TODO token 过期，退回登录页提示用户登录
                        ToastUtils.show(e.message)
                        return
                    }
                }

                // 使用缺省页作用域时抛出的的异常
                override fun onStateError(e: Throwable, view: View) {
                    e.printStackTrace()
                }
            })
        }
    }

    // 初始化 Toast
    private fun initToast(context: Context) {
        ToastUtils.init(context.applicationContext as Application)
        ToastUtils.setDebugMode(BuildConfig.DEBUG)
    }

    private fun initRefreshLayout(context: Context) {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, _ -> MaterialHeader(context) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ -> ClassicsFooter(context) }
        PageRefreshLayout.startIndex = 0
    }
}