package com.lu.plate.template.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.lu.plate.Plate
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseRVComponent
import com.lu.plate.ui.databinding.LeftImgLayoutImgBinding
import com.lu.plate.util.GsonUtil
import com.lu.plate.util.optJsonObject
import com.lu.plate.util.optString
import com.lu.plate.util.resolve.StyleComposite

class LeftImgLayoutRVComponent(
    plate: Plate,
    viewType: Int
) : BaseRVComponent(plate, viewType) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        val binding =
            LeftImgLayoutImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasePlateRecyclerAdapter.BVH(this, binding.root)
    }

    override fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        super.onBindView(adapter, holder, position)
        val binding = LeftImgLayoutImgBinding.bind(holder.itemView)
        adapter.getItem(position)?.props?.let {
            applyCardViewEx(binding, it)
            applyHeaderImg(binding, it)
            binding.cardTitle.text = it.optString("title")
            binding.cardTitleSub.text = it.optString("subTitle")
        }

    }

    private fun applyHeaderImg(
        binding: LeftImgLayoutImgBinding,
        props: JsonObject
    ) {
        val img = props.optString("img").ifBlank { null } ?: return
        Glide.with(binding.root.context)
            .load(img.trim())
            .disallowHardwareConfig()
            .into(binding.ivHeader)
    }

    private fun applyCardViewEx(
        binding: LeftImgLayoutImgBinding,
        props: JsonObject
    ) {
        props.optJsonObject("cardViewEx")?.let {
            GsonUtil.fromJson(it.toString(), CardViewEx::class.java)
        }?.let {
            binding.cardViewEle.apply {
                if (it.cardBackgroundColor != null) {
                    setCardBackgroundColor(StyleComposite.color.parseColor(it.cardBackgroundColor))
                }
                cardElevation = StyleComposite.size.parseFloat(it.cardElevation)
                radius = StyleComposite.size.parseFloat(it.cardCornerRadius)
            }
        }

    }


    private class CardViewEx(
        var cardBackgroundColor: String? = null,
        var cardCornerRadius: String? = null,
        var cardElevation: String? = null
    )
}
