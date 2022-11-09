package com.lu.plate.component

import android.view.View
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseVHComponent

class TextComponent(plate: Plate, itemView: View, templateId: Int) :
    BaseVHComponent(plate, itemView, templateId) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        TODO("Not yet implemented")
    }

    override fun refresh(data: Content) {
        TODO("Not yet implemented")
    }

}