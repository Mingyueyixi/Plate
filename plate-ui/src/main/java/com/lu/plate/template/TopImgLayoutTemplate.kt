package com.lu.plate.template

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.template.component.SVComponent
import com.lu.plate.ui.databinding.TopImageLayoutBinding
import com.lu.plate.util.GlideUtil
import com.lu.plate.util.optJsonObject
import com.lu.plate.util.optString

class TopImgLayoutTemplate(plate: Plate, templateId: Int) : BaseTemplate(plate, templateId) {

    override fun createSVComponent(parent: ViewGroup): SVComponent {
        return object : SVComponent {
            override fun onCreateView(
                plateStructure: PlateStructure,
                index: Int,
                content: Content?
            ): View {
                val binding = TopImageLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                if (content != null) {
                    initView(binding, content)
                }
                return binding.root
            }

            private fun initView(binding: TopImageLayoutBinding, content: Content) {
                content.props?.optJsonObject("img")?.let { img ->
                    val url = img.optString("value")
                    val ty = img.optString("type")
                    loadImage(binding.ivTopFrame, url, ty) {
                        content.props?.optJsonObject("fallbackImg")?.let { fallbackImg ->
                            val fallbackUrl = fallbackImg.optString("value")
                            val fallbackTy = fallbackImg.optString("type")
                            loadImage(binding.ivTopFrame, fallbackUrl, fallbackTy)
                        }

                    }
                }
                content.props?.optString("title")?.let {
                    binding.tvTitle.text = it
                }
                content.props?.optString("subTitle")?.let {
                    binding.tvSubTitle.text = it
                }
            }

            private fun loadImage(
                imageView: ImageView,
                value: String,
                ty: String,
                onFailFunc: GlideUtil.OnLoadFailedListener? = null
            ) {
                when (ty) {
                    "net" -> {
                        GlideUtil.with(parent)
                            .onFailed(onFailFunc)
                            .onSetResFunc {
                                fitRatioByLayoutParams(imageView, it)
                            }
                            .load(imageView, value)
                    }
                    "raw" -> {
                        val resId = parent.context.resources.getIdentifier(
                            value,
                            "raw",
                            parent.context.packageName
                        )
                        if (resId == 0) {
                            return
                        }
                        GlideUtil.with(parent)
                            .onFailed(onFailFunc)
                            .onSetResFunc {
                                fitRatioByLayoutParams(imageView, it)
                            }
                            .load(imageView, resId)
                    }
                }

            }

            fun fitRatioByLayoutParams(view: View, drawable: Drawable?) {
                drawable?.let {
                    var lp = view.layoutParams
                    if (lp is ConstraintLayout.LayoutParams) {
                        val w = it.intrinsicWidth
                        val h = it.intrinsicHeight
                        if (w > 0 && h > 0) {
                            lp.dimensionRatio = "$w:$h"
                        }
                    }
                }
            }
        }

    }

}