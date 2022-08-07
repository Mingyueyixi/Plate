package com.lu.plate.recycler.component

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.data.ComponentModel

abstract class BindingRecyclerComponent<VB : ViewBinding>(
    templateId: Int
) : BaseRecyclerComponent(templateId) {

    override fun createViewHolder(
        adapter: BasePlateRecyclerAdapter,
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        return createViewHolderVB(adapter, parent, viewType)
    }

    abstract fun createViewHolderVB(
        adapter: BasePlateRecyclerAdapter,
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BindVH<VB>

    override fun onBindViewHolder(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        if (holder is BasePlateRecyclerAdapter.BindVH<*>) {
            onBindViewHolderVB(adapter, holder as BasePlateRecyclerAdapter.BindVH<VB>, position)
        }
    }

    abstract fun onBindViewHolderVB(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BindVH<VB>,
        position: Int
    )


}