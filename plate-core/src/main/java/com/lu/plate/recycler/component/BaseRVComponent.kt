package com.lu.plate.recycler.component

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import com.lu.plate.Plate
import com.lu.plate.template.component.RVComponent
import com.lu.plate.data.Style
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.util.resolve.StyleComposite

/***
 * apply style for component
 */
abstract class BaseRVComponent(
    var plate: Plate,
    var itemView: View,
    var templateId: Int
) : RVComponent {


    override fun onBindView(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        applyContentStyle(adapter, holder, position)
    }

    /**
     * 设置content的样式
     */
    protected open fun applyContentStyle(
        adapter: BasePlateRecyclerAdapter,
        holder: BasePlateRecyclerAdapter.BVH,
        position: Int
    ) {
        val content = adapter.getItem(position)
        content?.style?.apply {
            //布局参数
            val lp = holder.itemView.layoutParams
            if (lp != null) {
                applyLayoutParams(this, lp)
            }
            applyPadding(this, holder.itemView)
            //背景
            background?.let {
                holder.itemView.background = StyleComposite.background.create(it)
            }
        }
    }

    private fun applyPadding(style: Style, itemView: View) {
        style.__archive__?.let {
            val l = it.paddingLeft ?: itemView.paddingLeft
            val t = it.paddingTop ?: itemView.paddingTop
            val r = it.paddingRight ?: itemView.paddingRight
            val b = it.paddingBottom ?: itemView.paddingBottom
            itemView.setPadding(l, t, r, b)
        }

    }

    private fun applyLayoutParams(style: Style, lp: ViewGroup.LayoutParams) {
        style.__archive__?.apply {
            width?.let {
                lp.width = it
            }
            height?.let {
                lp.height = it
            }
            if (lp is MarginLayoutParams) {
                marginLeft?.let {
                    lp.leftMargin = it
                }
                marginRight?.let {
                    lp.rightMargin = it
                }
                marginTop?.let {
                    lp.topMargin = it
                }
                marginBottom?.let {
                    lp.bottomMargin = it
                }
            }
        }

    }

}