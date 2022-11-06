package com.lu.plate.template

import com.lu.plate.Plate

open class BaseTemplate(var plate: Plate, private val templateId: Int) : Template {

    override fun getTemplateId(): Int {
        return templateId
    }
}