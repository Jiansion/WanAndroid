package com.conch.wanandroid.adapter

import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.conch.wanandroid.R
import com.conch.wanandroid.model.BannerItemModel
import com.conch.wanandroid.utils.log.LogCat
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * @author YeJain
 * @date 2022/8/11 18:40
 */
class BannerAdapter : BaseBannerAdapter<BannerItemModel>() {
    override fun bindData(
        holder: BaseViewHolder<BannerItemModel>,
        data: BannerItemModel,
        position: Int,
        pageSize: Int
    ) {
        holder.findViewById<ImageView>(R.id.iv).load(data.imagePath)
        holder.findViewById<TextView>(R.id.tv).text = data.title
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner_content
    }
}