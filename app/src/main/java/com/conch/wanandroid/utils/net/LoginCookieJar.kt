package com.conch.wanandroid.utils.net

import android.content.Context
import com.conch.wanandroid.database.AppDatabase
import com.conch.wanandroid.model.CookieModel
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * @author YeJain
 * @date 2022/8/8 11:49
 */
class LoginCookieJar(val context: Context) : CookieJar {
    private val dao = AppDatabase.getDB(context).cookieDao()

    // 在发送请求时添加 cookie，可返回一个为空 列表
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return getCookies(url)
    }

    // 请求成功后获取到的 Cookie，需要对Cookie 进行保存操作/登出时清理cookie
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        saveCookie(url, cookies)
    }


    /**
     * 获取已保存的cookie
     */
    private fun getCookies(url: HttpUrl): List<Cookie> {
        val list = mutableListOf<Cookie>()
        dao.gatByUrl(url = url.host).forEach { cookieModel ->
            Cookie.parse(url, cookieModel.cookie)?.let { cookie ->
                if (cookie.expiresAt > System.currentTimeMillis()) {
                    list.add(cookie)
                } else {
                    dao.delete(cookieModel)
                }
            }
        }
        return list
    }


    /**
     * 保存新的的cookie
     */
    private fun saveCookie(url: HttpUrl, cookies: List<Cookie>) {
        cookies.forEach { cookie ->
            if (cookie.expiresAt > System.currentTimeMillis()) {
                val model =
                    CookieModel(name = cookie.name, url = url.host, cookie = cookie.toString())
                dao.insertAll(model)
            }
        }
    }
}