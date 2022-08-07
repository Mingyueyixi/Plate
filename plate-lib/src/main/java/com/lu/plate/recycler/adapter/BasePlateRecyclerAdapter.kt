package com.lu.plate.recycler.adapter

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lu.plate.PlateManager
import com.lu.plate.recycler.data.ComponentModel
import com.lu.plate.recycler.data.PlateStructure
import com.lu.plate.recycler.component.BaseRecyclerComponent
import com.lu.plate.recycler.component.ToDoRecyclerComponent

open class BasePlateRecyclerAdapter(
    var plateManager: PlateManager,
    var viewData: PlateStructure
) : RecyclerView.Adapter<BasePlateRecyclerAdapter.BVH>() {

    open class BVH(itemView: View, var template: BaseRecyclerComponent) :
        RecyclerView.ViewHolder(itemView) {}

    open class BaseVH(itemView: View, template: BaseRecyclerComponent) :
        BVH(itemView, template) {
        private val mViews: SparseArray<View?> = SparseArray()
        fun <T : View> getView(id: Int): T? {
            var v = mViews[id]
            if (v == null) {
                v = itemView.findViewById(id)
                mViews.put(id, v)
            }
            return v as T?
        }
    }

    open class BindVH<VB : ViewBinding>(var binding: VB, template: BaseRecyclerComponent) :
        BaseVH(binding.root, template)

    open fun refreshViewData(vo: PlateStructure) {
        viewData = vo
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return getTemplateId(position) ?: return super.getItemViewType(position)
    }

    private fun getTemplateId(position: Int): Int? {
        var content = getItem(position)
        return content?.componentId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BVH {
        var template = plateManager.templateStore[viewType]
        if (template is BaseRecyclerComponent) {
            return template.createViewHolder(this, parent, viewType)
        }
        return ToDoRecyclerComponent().createViewHolder(this, parent, viewType)
    }

    override fun onBindViewHolder(holder: BVH, position: Int) {
        // holder.onBindView(viewData, position)
        holder.template.onBindViewHolder(this, holder, position)
    }

    fun getItem(position: Int): ComponentModel? {
        return viewData.components[position]
    }

    override fun getItemCount(): Int {
        return viewData.components.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewData.components.forEach {
            val template = plateManager.templateStore[it.componentId]
            if (template is BaseRecyclerComponent) {
                template.onAttachedToRecyclerView(recyclerView)
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        viewData.components.forEach {
            val template = plateManager.templateStore[it.componentId]
            if (template is BaseRecyclerComponent) {
                template.onDetachedFromRecyclerView(recyclerView)
            }
        }
    }

}