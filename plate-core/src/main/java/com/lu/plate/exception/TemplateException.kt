package com.lu.plate.exception

import com.lu.plate.template.Template

class TemplateException(var template: Template, message: String) : Exception(message) {

    override fun toString(): String {
        return "${javaClass.name}: $localizedMessage \n ${template.getTemplateId()}"
    }
}