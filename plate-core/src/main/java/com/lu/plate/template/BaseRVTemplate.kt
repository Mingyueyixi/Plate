package com.lu.plate.template

import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.recycler.component.BaseVHComponent

abstract class BaseRVTemplate(plate: Plate, templateId: Int) : BaseTemplate(plate, templateId) {
    abstract fun onCreateComponent(parent: ViewGroup, viewType: Int): BaseVHComponent
}
