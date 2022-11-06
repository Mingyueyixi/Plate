package com.lu.plate.recycler.component

import android.view.View
import com.lu.plate.Plate
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.data.Content

class InvalidComponent(plate: Plate, itemview: View) : BaseVHComponent(plate, itemview, -1) {
    override fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {

    }

    override fun refresh(data: Content) {

    }


}