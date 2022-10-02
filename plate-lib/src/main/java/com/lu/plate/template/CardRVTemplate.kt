package com.lu.plate.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lu.plate.databinding.CardLayoutBinding
import com.lu.plate.recycler.adapter.BasePlateRecyclerAdapter
import com.lu.plate.recycler.component.BaseVHComponent
import com.lu.plate.data.Content

class CardRVTemplate(templateId: Int) : BaseRVTemplate(templateId) {

    override fun onCreateComponent(parent: ViewGroup, viewType: Int): BaseVHComponent {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardComponent(binding, binding.root, viewType)
    }

    class CardComponent(
        private val binding: CardLayoutBinding,
        itemView: View,
        viewType: Int
    ) : BaseVHComponent(itemView, viewType) {

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