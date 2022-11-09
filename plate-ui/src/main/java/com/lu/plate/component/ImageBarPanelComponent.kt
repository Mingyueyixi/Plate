package com.lu.plate.component

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseVHComponent
import com.lu.plate.ui.databinding.CardLayoutBinding
import com.lu.plate.util.GsonUtil
import com.lu.plate.util.optJsonObject
import com.lu.plate.util.optString
import com.lu.plate.util.resolve.StyleComposite

class ImageBarPanelComponent(
    plate: Plate,
    itemView: View,
    viewType: Int
) : BaseVHComponent(plate, itemView, viewType) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        return BasePlateRecyclerAdapter.BVH(this, itemView)
    }

    override fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        super.onBindView(adapter, holder, position)
        val binding = CardLayoutBinding.bind(holder.itemView)
        adapter.getItem(position)?.props?.let {
            applyCardViewEx(binding, holder, it)
            applyHeaderImg(binding, holder, it)
            binding.cardTitle.text = it.optString("title")
            binding.cardTitleSub.text = it.optString("subTitle")
        }
    }

    private fun applyHeaderImg(
        binding: CardLayoutBinding,
        holder: BasePlateRecyclerAdapter.BVH,
        props: JsonObject
    ) {
        val img = props.optString("img") ?: return
        Glide.with(binding.root.context)
            .load(img.trim())
            .disallowHardwareConfig()
            .into(binding.ivHeader)
    }

    private fun applyCardViewEx(
        binding: CardLayoutBinding,
        holder: BasePlateRecyclerAdapter.BVH,
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

    override fun refresh(data: Content) {

    }

    private class CardViewEx(
        var cardBackgroundColor: String? = null,
        var cardCornerRadius: String? = null,
        var cardElevation: String? = null
    )
}
