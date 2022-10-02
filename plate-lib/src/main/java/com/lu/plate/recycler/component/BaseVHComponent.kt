package com.lu.plate.recycler.component

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import com.lu.plate.component.Component
import com.lu.plate.data.Style
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.util.StyleResolution

abstract class BaseVHComponent(
    itemView: View,
    templateId:Int
) : BasePlateRecyclerAdapter.BVH(itemView), Component {


    open fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        applyContentStyle(adapter, holder, position)
    }

    /**
     * 设置content的样式
     */
    private fun applyContentStyle(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        val content = adapter.getItem(position)
        content?.style?.apply {
            applyPadding(this, holder.itemView)

            //背景
            background?.let {
                holder.itemView.background = ShapeBackground.from(it)
            }

            val lp = itemView.layoutParams
            if (lp != null) {
                applyLayoutParams(this, lp)
            }

        }
    }

    private fun applyPadding(style: Style, itemView: View) {
        val l = StyleResolution.parseSize(style.paddingLeft) ?: itemView.paddingLeft
        val t = StyleResolution.parseSize(style.paddingTop) ?: itemView.paddingTop
        val r = StyleResolution.parseSize(style.paddingRight) ?: itemView.paddingRight
        val b = StyleResolution.parseSize(style.paddingBottom) ?: itemView.paddingBottom
        itemView.setPadding(l, t, r, b)
    }

    private fun applyLayoutParams(style: Style, lp: ViewGroup.LayoutParams) {
        val w = StyleResolution.parseSize(style.width)
        if (w != null) {
            lp.width = w
        }

        val h = StyleResolution.parseSize(style.height)
        if (h != null) {
            lp.height = h
        }

        if (lp is MarginLayoutParams) {
            StyleResolution.parseSize(style.marginLeft)?.let {
                lp.leftMargin = it
            }
            StyleResolution.parseSize(style.marginRight)?.let {
                lp.rightMargin = it
            }
            StyleResolution.parseSize(style.marginTop)?.let {
                lp.topMargin = it
            }
            StyleResolution.parseSize(style.marginBottom)?.let {
                lp.bottomMargin = it
            }
        }

    }

}