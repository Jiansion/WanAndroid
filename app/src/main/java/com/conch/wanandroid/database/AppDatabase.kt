package com.conch.wanandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.conch.wanandroid.dao.CookieDao
import com.conch.wanandroid.model.CookieModel

/**
 * @author YeJain
 * @date 2022/8/9 11:12
 */
@Database(entities = [CookieModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null

        fun getDB(context: Context): AppDatabase {
            val database = db ?: kotlin.run {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "wanandroid.db"
                ).build()
                return@run db!!
            }
            return database
        }
    }

    abstract fun cookieDao(): CookieDao
}