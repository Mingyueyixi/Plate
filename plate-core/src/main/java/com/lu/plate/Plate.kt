package com.lu.plate

import android.app.Application
import com.lu.plate.data.PlateStructure
import com.lu.plate.exception.TemplateException
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.template.Template
import com.lu.plate.util.resolve.BackgroundResolve
import com.lu.plate.util.resolve.StyleComposite

class Plate {
    val templateStore = mutableMapOf<Int, Template>()

    companion object {
        lateinit var context: Application
        fun doInit(ctx: Application) {
            context = ctx
        }
    }

    fun register(vararg templates: Template): Plate {
        registerInternal(false, *templates)
        return this
    }

    fun registerWithCover(vararg templates: Template): Plate {
        registerInternal(false, *templates)
        return this
    }

    private fun registerInternal(check: Boolean = false, vararg templates: Template) {
        for (template in templates) {
            if (check) {
                checkTemplateVail(template)
            }
            val templateId = template.getTemplateId()
            templateStore[templateId] = template
        }
    }

    private fun checkTemplateVail(template: Template) {
        val templateId = template.getTemplateId()
        if (templateStore.containsKey(templateId)) {
            throw TemplateException(template, "templateId $templateId repeat")
        }
    }

    fun createRecyclerAdapter(data: PlateStructure): BasePlateRecyclerAdapter {
        return BasePlateRecyclerAdapter(this, data)
    }

    fun setStyleCompositeIMP(imp: StyleComposite.IMPInterface): Plate {
        StyleComposite.IMP = imp
        return this
    }


}