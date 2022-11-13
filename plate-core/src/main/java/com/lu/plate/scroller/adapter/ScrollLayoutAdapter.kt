package com.lu.plate.scroller.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.template.component.NotImplComponent


class ScrollLayoutAdapter(var plate: Plate, var plateStructure: PlateStructure) : BaseAdapter() {
    private var mOnClickListener: OnClickListener? = null

    override fun getCount(): Int {
        return plateStructure.contents.size
    }

    override fun getItem(position: Int): Content? {
        return if (position in 0 until count) {
            plateStructure.contents[position]
        } else null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val templateId = getItem(position)?.templateId
        val template = plate.templateStore[templateId]
        val component = if (parent != null && template != null) {
            template.createSVComponent(parent)
        } else {
            //template not register
            NotImplComponent.INSTANCE
        }
        val content = getItem(position)
        val itemView = component.onCreateView(plateStructure, position, content)
        content?.style?.let { component.applyContentStyle(it, itemView) }

        itemView.setOnClickListener {
            mOnClickListener?.onClick(this, it, content, position)
        }
        return itemView
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.mOnClickListener = listener
    }

    //kotlin fun interface like java @FunctionalInterface
    fun interface OnClickListener {
        fun onClick(adapter: ScrollLayoutAdapter, view: View, content: Content?, position: Int)
    }

}
