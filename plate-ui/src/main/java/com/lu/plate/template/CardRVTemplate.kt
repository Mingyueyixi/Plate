package com.lu.plate.template

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.component.CardComponent
import com.lu.plate.recycler.component.BaseVHComponent
import com.lu.plate.ui.databinding.CardLayoutBinding

class CardRVTemplate(plate: Plate, templateId: Int) : BaseRVTemplate(plate, templateId) {

    override fun onCreateComponent(parent: ViewGroup, viewType: Int): BaseVHComponent {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardComponent(plate, binding.root, viewType)
    }


}