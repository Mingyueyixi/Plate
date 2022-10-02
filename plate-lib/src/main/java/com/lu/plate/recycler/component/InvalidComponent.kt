package com.lu.plate.recycler.component

import android.view.View
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.data.Content

class InvalidComponent(itemview: View) : BaseVHComponent(
    itemview, -1
) {
    override fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {

    }

    override fun refresh(data: Content) {

    }


}