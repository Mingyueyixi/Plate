package com.lu.plate.recycler.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.template.BaseTemplate
import com.lu.plate.template.component.NotImplComponent
import com.lu.plate.template.component.RVComponent

open class BasePlateRecyclerAdapter(
    var plate: Plate,
    var dataSource: PlateStructure
) : RecyclerView.Adapter<BasePlateRecyclerAdapter.BVH>() {
    private var mOnClickListener: ((BasePlateRecyclerAdapter, View, Int) -> Unit)? = null

    open class BVH(var component: RVComponent, itemView: View) : RecyclerView.ViewHolder(itemView)

    open fun refreshView(vo: PlateStructure) {
        dataSource = vo
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        var item = getItem(position)
        return item?.templateId ?: RecyclerView.INVALID_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BVH {
        val template = plate.templateStore[viewType]
        val component = if (template is BaseTemplate) {
            template.createRVComponent(parent, viewType)
        } else {
            NotImplComponent.INSTANCE
        }
        val holder = component.onCreateViewHolder(parent, viewType)
        holder.itemView.setOnClickListener {
            mOnClickListener?.invoke(this, it, holder.layoutPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: BVH, position: Int) {
        holder.component.onBindView(this, holder, position)
    }

    fun getItem(position: Int): Content? {
        if (position >= 0 && position < dataSource.contents.size) {
            return dataSource.contents[position]
        }
        return null
    }

    override fun getItemCount(): Int {
        return dataSource.contents.size
    }

    open fun setOnClickListener(listener: (BasePlateRecyclerAdapter, View, Int) -> Unit) {
        mOnClickListener = listener
    }


}