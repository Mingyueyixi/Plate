package com.lu.plate.template.component

import android.view.View
import android.view.ViewGroup
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter

class NotImplComponent : RVComponent, SVComponent {

    companion object {
        val INSTANCE = NotImplComponent()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasePlateRecyclerAdapter.BVH {
        TODO("Not yet implemented")
        // the block compile will like:
        // throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    override fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        plateStructure: PlateStructure,
        index: Int,
        content: Content?
    ): View {
        TODO("Not yet implemented")
    }

}