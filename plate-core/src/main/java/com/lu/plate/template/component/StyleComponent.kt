package com.lu.plate.template.component

import android.view.View
import android.view.ViewGroup
import com.lu.plate.data.Style
import com.lu.plate.util.resolve.StyleComposite

interface StyleComponent : Component {

    fun applyContentStyle(
        style: Style,
        itemView: View
    ) = IMPL.applyContentStyle(style, itemView)

    class StyleImpl : StyleComponent {
        override fun applyContentStyle(style: Style, itemView: View) {
            style.apply {
                //布局参数
                val lp = itemView.layoutParams
                if (lp != null) {
                    applyLayoutParams(this, lp)
                }
                applyPadding(this, itemView)
                //背景
                background?.let {
                    itemView.background = StyleComposite.background.create(it)
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
                if (lp is ViewGroup.MarginLayoutParams) {
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

    companion object {
        val IMPL: StyleImpl = StyleImpl()
    }
}