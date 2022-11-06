package com.lu.plate.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.databinding.CardLayoutBinding
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseVHComponent
import com.lu.plate.data.Content

class CardRVTemplate(plate: Plate, templateId: Int) : BaseRVTemplate(plate, templateId) {

    override fun onCreateComponent(parent: ViewGroup, viewType: Int): BaseVHComponent {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardComponent(plate, binding, binding.root, viewType)
    }

    class CardComponent(
        plate: Plate,
        private val binding: CardLayoutBinding,
        itemView: View,
        viewType: Int
    ) : BaseVHComponent(plate, itemView, viewType) {

        override fun onBindView(
            adapter: BasePlateRecyclerAdapter,
            holder: BasePlateRecyclerAdapter.BVH,
            position: Int
        ) {
            super.onBindView(adapter, holder, position)
        }

        override fun refresh(data: Content) {

        }

    }

}