package com.lu.plate.component

import com.lu.plate.recycler.data.ComponentModel


abstract class BaseComponent(
    private val templateId: Int
) : Component {

    override fun getComponentId(): Int {
        return templateId
    }

}