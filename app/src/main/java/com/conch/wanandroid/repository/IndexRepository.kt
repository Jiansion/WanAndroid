package com.conch.wanandroid.repository

import com.conch.wanandroid.base.BaseResponse
import com.conch.wanandroid.constants.Api
import com.conch.wanandroid.model.BannerItemModel
import com.conch.wanandroid.model.IndexModel
import com.drake.net.Get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author YeJain
 * @date 2022/8/11 13:49
 */
class IndexRepository(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) {

    suspend fun fetchIndexFirstData() = withContext(dispatcher) {
        val banner = Get<BaseResponse<List<BannerItemModel>>>(Api.BANNER_URL)
        val indexList = Get<BaseResponse<IndexModel>>(Api.indexDataUrl())
        Pair(banner.await(), indexList.await())
    }


    /**
     * 获取 Banner 数据
     */
    suspend fun fetchBanner() = withContext(dispatcher) {
        Get<BaseResponse<List<BannerItemModel>>>(Api.BANNER_URL).await()
    }


    /**
     * 获取首页文章列表
     */
    suspend fun fetchIndexList(index: Int) = withContext(dispatcher) {
        Get<BaseResponse<IndexModel>>(Api.indexDataUrl(index)).await()
    }
}