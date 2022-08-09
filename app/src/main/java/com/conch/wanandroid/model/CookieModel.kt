package com.conch.wanandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author YeJain
 * @date 2022/8/9 11:01
 */
@Entity(tableName = "cookies")
data class CookieModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val url: String,
    val name: String,
    val cookie: String
)