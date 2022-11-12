package com.lu.plate.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.demo.base.BindingFragment
import com.lu.plate.demo.databinding.FragmentSvDemoBinding
import com.lu.plate.demo.route.AppLinkRouter
import com.lu.plate.scroller.adapter.ScrollLayoutAdapter
import com.lu.plate.scroller.ScrollLayoutManager
import com.lu.plate.template.TopImgLayoutTemplate
import com.lu.plate.util.GsonUtil
import com.lu.plate.util.optString
import java.io.InputStreamReader

/**
 * component impl by scroll demo
 */
class SVDemoFragment : BindingFragment<FragmentSvDemoBinding>() {
    private lateinit var mPlateStructure: PlateStructure
    private val plate: Plate by lazy {
        PlateManager.createWithCommon()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding(FragmentSvDemoBinding.inflate(inflater, container, false))
        initData()
        initView()
        return binding.root
    }

    private fun initData() {
        mockRequestRemoteData()
    }

    private fun mockRequestRemoteData() {
        context?.assets?.open("plate_sample_sv.json")?.let {
            InputStreamReader(it).let { reader ->
                mPlateStructure = GsonUtil.fromJson(reader, PlateStructure::class.java)
                reader.close()
            }
            it.close()
        }
    }


    private fun initView() {
        val adapter = ScrollLayoutAdapter(plate, mPlateStructure)
        adapter.setOnClickListener { _, _, content, _ ->
            content?.props?.optString("clickLink").let {
                AppLinkRouter.route(link = it)
            }
        }
        ScrollLayoutManager(plate, binding.root, adapter).inflate()
    }
}