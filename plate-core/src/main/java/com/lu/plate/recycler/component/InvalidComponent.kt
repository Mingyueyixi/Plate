package com.lu.plate.recycler.component

import android.view.View
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.data.Content

class InvalidComponent(plate: Plate, itemView: View) : BaseVHComponent(plate, itemView, -1) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        return BasePlateRecyclerAdapter.BVH(this, parent)
    }

    override fun refresh(data: Content) {

    }


}