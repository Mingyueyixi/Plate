package com.lu.plate.template.component

import android.view.View
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseRVComponent

class TextComponent(plate: Plate, itemView: View, templateId: Int) :
    BaseRVComponent(plate, itemView, templateId) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        TODO("Not yet implemented")
    }

}