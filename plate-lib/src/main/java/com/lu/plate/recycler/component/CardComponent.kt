package com.lu.plate.recycler.component

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lu.plate.databinding.CardLayoutBinding
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.data.ComponentModel
import com.lu.plate.recycler.data.ShapeBackground

class CardComponent(
    componentId: Int
) : BindingRecyclerComponent<CardLayoutBinding>(componentId) {


    override fun createViewHolderVB(
        adapter: BasePlateRecyclerAdapter,
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BindVH<CardLayoutBinding> {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasePlateRecyclerAdapter.BindVH(binding, this)
    }

    override fun onBindViewHolderVB(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BindVH<CardLayoutBinding>,
        position: Int
    ) {
        var model = adapter.getItem(position)

        model?.style?.background?.let {
            holder.binding.root.background = ShapeBackground.from(it)
        }
        holder.binding.apply {
            cardTitle.text = "大标题"
            cardTitleSub.text = "小标题"
        }

    }

}