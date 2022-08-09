package com.conch.wanandroid.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.conch.wanandroid.WanAndroidApp
import com.conch.wanandroid.dao.CookieDao
import com.conch.wanandroid.model.CookieModel

/**
 * @author YeJain
 * @date 2022/8/9 11:12
 */
@Database(entities = [CookieModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val db =
            Room.databaseBuilder(WanAndroidApp.INSTANCE, AppDatabase::class.java, "wanandroid_db").build()
    }

    abstract fun cookieDao(): CookieDao
}