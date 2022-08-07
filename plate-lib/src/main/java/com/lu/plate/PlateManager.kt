package com.lu.plate

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.lu.plate.component.BaseComponent
import com.lu.plate.exception.TemplateException
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.data.PlateStructure

class PlateManager {
    val templateStore = mutableMapOf<Int, BaseComponent>()

    companion object {
        lateinit var context: Application
        fun doInit(ctx: Application) {
            context = ctx
        }
    }

    fun register(vararg templates: BaseComponent): PlateManager {
        registerInternal(false, *templates)
        return this
    }

    fun registerWithCover(vararg templates: BaseComponent): PlateManager {
        registerInternal(true, *templates)
        return this
    }

    private fun registerInternal(force: Boolean = false, vararg templates: BaseComponent) {
        for (template in templates) {
            if (force) {
                checkTemplateVail(template)
            }
            val templateId = template.getComponentId()
            templateStore[templateId] = template
        }
    }

    private fun checkTemplateVail(template: BaseComponent) {
        val templateId = template.getComponentId()
        if (templateStore.containsKey(templateId)) {
            throw TemplateException(template, "templateId $templateId repeat")
        }
    }

    fun createRecyclerAdapter(data: PlateStructure): BasePlateRecyclerAdapter {
        return BasePlateRecyclerAdapter(this, data)
    }

}