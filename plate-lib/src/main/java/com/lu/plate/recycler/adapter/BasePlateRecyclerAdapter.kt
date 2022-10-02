package com.lu.plate.recycler.adapter

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lu.plate.PlateManager
import com.lu.plate.recycler.component.BaseVHComponent
import com.lu.plate.recycler.component.InvalidComponent
import com.lu.plate.recycler.data.ContentWrap
import com.lu.plate.recycler.data.PlateStructure
import com.lu.plate.template.BaseRVTemplate

open class BasePlateRecyclerAdapter(
    var plateManager: PlateManager,
    var dataSource: PlateStructure
) : RecyclerView.Adapter<BasePlateRecyclerAdapter.BVH>() {

    open class BVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mViews: SparseArray<View?> = SparseArray()
        fun <T : View> getView(id: Int): T? {
            var v = mViews[id]
            if (v == null) {
                v = itemView.findViewById(id)
                mViews.put(id, v)
            }
            return v as T?
        }
    }

    open fun refreshView(vo: PlateStructure) {
        dataSource = vo
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        var item = getItem(position)
        return item?.templateId ?: RecyclerView.INVALID_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BVH {
        var template = plateManager.templateStore[viewType]
        if (template is BaseRVTemplate) {
            return template.onCreateComponent(parent, viewType)
        }
        return InvalidComponent(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: BVH, position: Int) {
        if (holder is BaseVHComponent) {
            holder.onBindView(this, holder, position)
            return
        }
        throw Exception("invalid holder!!!")
    }

    fun getItem(position: Int): ContentWrap? {
        if (position >= 0 && position < dataSource.contents.size) {
            return dataSource.contents[position]
        }
        return null
    }

    override fun getItemCount(): Int {
        return dataSource.contents.size
    }


}