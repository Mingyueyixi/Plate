package com.lu.plate.template

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.data.Element
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseRVComponent
import com.lu.plate.template.component.RVComponent
import com.lu.plate.ui.databinding.BannerAdLayoutBinding
import com.lu.plate.util.ImageUtil
import com.lu.plate.util.optString
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.util.LogUtils

class AdBannerTemplate(plate: Plate, templateId: Int) : BaseTemplate(plate, templateId) {

    override fun createRVComponent(parent: ViewGroup, viewType: Int): RVComponent {
        return AdBannerRVComponent(plate, viewType)
    }

    class AdBannerRVComponent(plate: Plate, templateId: Int) : BaseRVComponent(plate, templateId) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BasePlateRecyclerAdapter.BVH {
            return BannerAdLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                .let {
                    BasePlateRecyclerAdapter.BVH(this, it.root)
                }
        }

        override fun onBindView(
            adapter: BasePlateRecyclerAdapter,
            holder: BasePlateRecyclerAdapter.BVH,
            position: Int
        ) {
            val bindding = BannerAdLayoutBinding.bind(holder.itemView)
            val content = adapter.getItem(position)
            bindding.bannerAd.indicator = CircleIndicator(bindding.root.context)
            bindding.bannerAd.setAdapter(
                AdBannerImageAdapter(
                    content?.elements ?: arrayListOf()
                )
            )

        }

        inner class AdBannerImageAdapter(data: List<Element>) :
            BannerImageAdapter<Element>(data) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: Element?,
                position: Int,
                size: Int
            ) {
                if (holder == null || data == null) {
                    return
                }
                val url = data.props?.optString("img") ?: return
                ImageUtil.with(holder.itemView)
                    .onFailed {
                        LogUtils.e(Log.getStackTraceString(it))
                    }
                    .onReady {
                        LogUtils.e("ready")
                    }
                    .load(holder.imageView, url)

            }

        }

    }
}