package com.lu.plate.template

import android.view.ViewGroup
import com.lu.plate.template.component.NotImplComponent
import com.lu.plate.template.component.RVComponent
import com.lu.plate.template.component.SVComponent

interface Template {
    fun getTemplateId(): Int
    fun createRVComponent(parent: ViewGroup, viewType: Int): RVComponent = NotImplComponent.INSTANCE

    fun createSVComponent(parent: ViewGroup): SVComponent = NotImplComponent.INSTANCE
}