package com.lu.plate.exception

import com.lu.plate.template.Template

class TemplateException(var template: Template, message: String) : Exception(message) {

    override fun toString(): String {
        val s = javaClass.name
        val message = localizedMessage
        return "$s: $message \n $template"
    }
}