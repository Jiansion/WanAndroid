package com.conch.wanandroid.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.conch.wanandroid.model.CookieModel

/**
 * @author YeJain
 * @date 2022/8/9 11:03
 */
@Dao
interface CookieDao {
    @Query("select * from cookies")
    fun getAll(): List<CookieModel>

    @Query("select * from cookies where url = :url")
    fun gatByUrl(url: String): List<CookieModel>

    @Insert
    fun insertAll(vararg cookies: CookieModel)

    @Delete
    fun delete(cookie: CookieModel)

    @Query("delete from cookies where url = :url")
    fun deleteByUrl(url: String)
}