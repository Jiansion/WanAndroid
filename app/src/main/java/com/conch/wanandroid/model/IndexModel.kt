package com.conch.wanandroid.model

import com.squareup.moshi.JsonClass

/**
 * @author YeJain
 * @date 2022/8/5 15:16
 */
@JsonClass(generateAdapter = true)
data class IndexModel(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val datas: List<IndexItemModel>
)

@JsonClass(generateAdapter = true)
data class IndexItemModel(
    val id: Long,
    val link: String,
    val title: String,
    val shareUser: String,
    val superChapterId: Int, // 一级分类组名
    val superChapterName: String, // 一级分类名
    val chapterId: Int, // 二级分类Id
    val chapterName: String, // 二级分类名
    val publishTime: Long,
    val collect: Boolean, // 是否已收藏
)