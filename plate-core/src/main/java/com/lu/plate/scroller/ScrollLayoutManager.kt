package com.lu.plate.scroller

import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import com.lu.plate.Plate
import com.lu.plate.data.PlateStructure
import com.lu.plate.scroller.adapter.ScrollLayoutAdapter

open class ScrollLayoutManager(
    var plate: Plate,
    var scrollView: ScrollView,
    var adapter: ScrollLayoutAdapter
) {

    init {
        val dataSetObserver = ScrollLayoutDataSetObserver()
        adapter.registerDataSetObserver(dataSetObserver)
    }

    fun inflate() {
        val container = inflateContainerIfNeed()
        if (scrollView.childCount == 0) {
            scrollView.addView(
                container,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        inflateComponent(container, adapter.plateStructure)
    }

    private fun inflateContainerIfNeed(): ViewGroup {
        if (scrollView.childCount > 1) {
            throw Exception("Not allow scrollView's childCount > 1")
        }
        val container: View? = scrollView.getChildAt(0)
        if (container is ViewGroup) {
            //suggest: use vertical LinearLayout to show list-view
            //in scroll layout, other view maybe look not friendly or not work
            return container
        } else if (container == null) {
            return LinearLayout(scrollView.context).also {
                it.orientation = LinearLayout.VERTICAL
            }
        }
        throw Exception("Only allow scrollView's child is ViewGroup")
    }

    private fun inflateComponent(container: ViewGroup, plateStructure: PlateStructure) {
        plateStructure.contents.forEachIndexed { index, content ->
            val child = adapter.getView(index, null, container)
            container.addView(child)
        }
    }

    fun refresh(plateStructure: PlateStructure) {
        val container = inflateContainerIfNeed()
        container.removeAllViews()
        inflateComponent(container, plateStructure)
    }

    inner class ScrollLayoutDataSetObserver : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            refresh(adapter.plateStructure)
        }
    }

}
