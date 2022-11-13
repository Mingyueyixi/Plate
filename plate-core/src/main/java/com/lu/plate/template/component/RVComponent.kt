package com.lu.plate.template.component

import android.view.ViewGroup
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter

interface RVComponent : StyleComponent {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePlateRecyclerAdapter.BVH
    fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    )

}