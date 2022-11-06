package com.lu.plate.recycler.adapter

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lu.plate.Plate
import com.lu.plate.recycler.component.BaseVHComponent
import com.lu.plate.recycler.component.InvalidComponent
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.template.BaseRVTemplate

open class BasePlateRecyclerAdapter(
    var plate: Plate,
    var dataSource: PlateStructure
) : RecyclerView.Adapter<BasePlateRecyclerAdapter.BVH>() {
    private var mOnClickListener: OnClickListener? = null


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
        val template = plate.templateStore[viewType]
        val holder = if (template is BaseRVTemplate) {
            template.onCreateComponent(parent, viewType)
        } else {
            InvalidComponent(plate, TextView(parent.context))
        }
        holder.itemView.setOnClickListener {
            mOnClickListener?.onClick(this, it, holder.layoutPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: BVH, position: Int) {
        if (holder is BaseVHComponent) {
            holder.onBindView(this, holder, position)
            return
        }
        throw Exception("invalid holder!!!")
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

    open fun setOnClickListener(listener: OnClickListener) {
        mOnClickListener = listener
    }

    interface OnClickListener {
        fun onClick(adapter: BasePlateRecyclerAdapter, v: View, position: Int)

    }
}