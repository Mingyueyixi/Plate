package com.lu.plate.exception

import com.lu.plate.template.component.Component

class ComponentException(var component: Component, msg: String) : Exception(msg) {
    override fun toString(): String {
        return "${javaClass.name}: $localizedMessage \n $component"
    }
}