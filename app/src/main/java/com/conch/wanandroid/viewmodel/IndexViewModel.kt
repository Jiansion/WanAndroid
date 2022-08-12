package com.conch.wanandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeNetLife
import com.conch.wanandroid.model.BannerItemModel
import com.conch.wanandroid.model.IndexItemModel
import com.conch.wanandroid.model.IndexModel
import com.conch.wanandroid.repository.IndexRepository

/**
 * @author YeJain
 * @date 2022/8/11 13:57
 */
class IndexViewModel : ViewModel() {
    private val indexRepository = IndexRepository()

    private val _indexFirstLiveData =
        MutableLiveData<Pair<List<BannerItemModel>, List<IndexItemModel>>>()
    val indexFirstLiveData: LiveData<Pair<List<BannerItemModel>, List<IndexItemModel>>> =
        _indexFirstLiveData

    fun fetchIndexFirst() {
        scopeNetLife {
            val result = indexRepository.fetchIndexFirstData()
            val bannerData = result.first.data
            val indexModel = result.second.data

            if (bannerData != null && indexModel != null) {
                _indexFirstLiveData.value = Pair(bannerData, indexModel.datas)
            }

        }
    }


    fun fetchIndexList(index: Int) {
        scopeNetLife {
            val result = indexRepository.fetchIndexList(index)
        }
    }


}