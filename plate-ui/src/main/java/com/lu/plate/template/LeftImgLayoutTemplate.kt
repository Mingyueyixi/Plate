package com.lu.plate.template

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.template.component.LeftImgLayoutRVComponent
import com.lu.plate.template.component.RVComponent
import com.lu.plate.ui.databinding.LeftImgLayoutImgBinding

class LeftImgLayoutTemplate(plate: Plate, templateId: Int) : BaseTemplate(plate, templateId) {

    override fun createRVComponent(parent: ViewGroup, viewType: Int): RVComponent {
        return LeftImgLayoutRVComponent(plate, viewType)
    }

}