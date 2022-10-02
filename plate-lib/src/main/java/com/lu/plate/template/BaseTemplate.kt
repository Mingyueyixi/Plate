package com.lu.plate.template

import android.view.ViewGroup

open class BaseTemplate(private val templateId:Int):Template {

    override fun getTemplateId(): Int {
        return templateId
    }
}