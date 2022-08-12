package com.conch.wanandroid.ui.index

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.conch.wanandroid.R
import com.conch.wanandroid.adapter.BannerAdapter
import com.conch.wanandroid.base.viewBinding
import com.conch.wanandroid.databinding.FragmentIndexBinding
import com.conch.wanandroid.ext.dp
import com.conch.wanandroid.model.BannerItemModel
import com.conch.wanandroid.model.IndexItemModel
import com.conch.wanandroid.viewmodel.IndexViewModel
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorGravity

/**
 * @author YeJain
 * @date 2022/8/11 14:37
 */
class IndexFragment : Fragment(R.layout.fragment_index) {

    private val binding: FragmentIndexBinding by viewBinding(FragmentIndexBinding::bind)

    private val indexViewModel by viewModels<IndexViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        bindData()
    }

    private fun initView() {
        binding.rv.linear()
            .divider {
                setDivider(1.dp)
                setColorRes(R.color.color_222222)
                addType(R.layout.item_news)
            }
            .setup {
                addType<IndexItemModel>(R.layout.item_news)
                addType<List<BannerItemModel>>(R.layout.item_banner)
                onCreate {
                    if (it == R.layout.item_banner) {
                        findView<BannerViewPager<BannerItemModel>>(R.id.banner).apply {
                            adapter = BannerAdapter()
                            setIndicatorGravity(IndicatorGravity.END)
                            setLifecycleRegistry(lifecycle)
                        }
                    }
                }
                onBind {
                    when (itemViewType) {
                        R.layout.item_news -> {
                            val model = getModel<IndexItemModel>()
                            findView<TextView>(R.id.tvAuthor).text = model.shareUser
                            findView<TextView>(R.id.tvShareTime).text = model.publishTime.toString()
                            findView<TextView>(R.id.tvTitle).text = model.title
                        }
                        R.layout.item_banner -> {
                            val model = getModel<List<BannerItemModel>>()
                            findView<BannerViewPager<BannerItemModel>>(R.id.banner).apply {
                                if (model != data) {
                                    create(getModel())
                                }
                            }
                        }
                    }
                }
            }

        binding.pageRefresh.run {
            onRefresh {
                indexViewModel.fetchIndexFirst()
            }
            onLoadMore {
                indexViewModel.fetchIndexList(index)
            }
            showLoading()
        }
    }

    private fun bindData() {
        // 获取第一页数据,Banner 和 列表数据
        indexViewModel.indexFirstLiveData.observe(viewLifecycleOwner) { data ->
            binding.pageRefresh.showContent()
            val list = ArrayList<Any>()
            list.add(data.first)
            list.addAll(data.second)
            binding.rv.models = list
        }
    }

}