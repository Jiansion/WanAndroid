package com.conch.wanandroid.model

import com.squareup.moshi.JsonClass

/**
 * @author YeJain
 * @date 2022/8/11 13:46
 */
@JsonClass(generateAdapter = true)
data class BannerItemModel(
    val title: String,
    val imagePath: String,
    val url: String
)
