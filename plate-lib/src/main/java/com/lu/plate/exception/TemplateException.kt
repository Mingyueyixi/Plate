package com.lu.plate.exception

import com.lu.plate.component.Component

class TemplateException(var template: Component, message: String) : Exception(message) {

    override fun toString(): String {
        val s = javaClass.name
        val message = localizedMessage
        return "$s: $message \n $template"
    }
}