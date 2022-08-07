package com.lu.plate.recycler.component

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.data.ComponentModel
import com.lu.plate.component.BaseComponent

abstract class BaseRecyclerComponent(
    templateId: Int
) : BaseComponent(templateId) {
//    var mAdapter: BasePlateRecyclerAdapter? = null

    abstract fun createViewHolder(
        adapter: BasePlateRecyclerAdapter,
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH

    abstract fun onBindViewHolder(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    )

    override fun refresh(componentModel: ComponentModel) {
//        this.model = componentModel
//        val position = plateStructure.contents.indexOf(content)
//        if (position >= 0) {
//            mAdapter?.notifyItemChanged(position)
//        }
    }

    fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

    }

    fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {

    }

}