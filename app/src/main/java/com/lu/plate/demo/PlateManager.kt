package com.lu.plate.demo

import android.content.Context
import com.lu.plate.Plate
import com.lu.plate.template.AdBannerTemplate
import com.lu.plate.template.LeftImgLayoutTemplate
import com.lu.plate.template.TopImgLayoutTemplate

class PlateManager {
    companion object {
        fun doInit(context: Context) {
            Plate.doInit(context)
        }

        fun createWithCommon(): Plate {
            return Plate().also {
                it.register(LeftImgLayoutTemplate(it, 1))
                it.register(TopImgLayoutTemplate(it, 2))
                it.register(AdBannerTemplate(it, 3))
            }
        }
    }
}