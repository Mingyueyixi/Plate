package com.lu.plate.recycler.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.component.Component

abstract class BaseVHComponent(
    var itemView: View,
    private val templateId: Int
) : BasePlateRecyclerAdapter.BVH(itemView), Component {

    abstract fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    )

}