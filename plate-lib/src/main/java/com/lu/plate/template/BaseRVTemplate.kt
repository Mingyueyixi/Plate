package com.lu.plate.template

import android.view.ViewGroup
import com.lu.plate.recycler.component.BaseVHComponent

abstract class BaseRVTemplate(templateId: Int) : BaseTemplate(templateId) {
    abstract fun onCreateComponent(parent: ViewGroup, viewType: Int): BaseVHComponent
}
