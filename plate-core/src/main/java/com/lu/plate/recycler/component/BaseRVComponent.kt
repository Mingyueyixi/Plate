package com.lu.plate.recycler.component

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import com.lu.plate.Plate
import com.lu.plate.template.component.RVComponent
import com.lu.plate.data.Style
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.util.resolve.StyleComposite

/***
 * apply style for component
 */
abstract class BaseRVComponent(
    var plate: Plate,
    var templateId: Int
) : RVComponent