package com.lu.plate.template.component

import android.view.View
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure

interface SVComponent : Component {

    fun onCreateView(
        plateStructure: PlateStructure,
        index: Int,
        content: Content?
    ): View

}