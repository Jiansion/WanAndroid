package com.conch.wanandroid.model

import com.squareup.moshi.JsonClass

/**
 * @author YeJain
 * @date 2022/8/8 11:58
 */
@JsonClass(generateAdapter = true)
data class UserModel(
    val admin: Boolean,
    val coinCount: Int,
    val email: String,
    val icon: String,
    val id: String,
    val nickname: String,
    val publicName: String,
    val type: Int,
    val username: String
)