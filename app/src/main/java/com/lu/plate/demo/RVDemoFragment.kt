package com.lu.plate.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lu.plate.Plate
import com.lu.plate.data.PlateStructure
import com.lu.plate.demo.base.BindingFragment
import com.lu.plate.demo.databinding.FragmentRvDemoBinding
import com.lu.plate.template.LeftImgLayoutTemplate
import com.lu.plate.util.GsonUtil
import java.io.InputStreamReader

class RVDemoFragment : BindingFragment<FragmentRvDemoBinding>() {

    private val plate: Plate by lazy {
        PlateManager.createWithCommon()
    }

    private lateinit var mPlateStructure: PlateStructure
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding(FragmentRvDemoBinding.inflate(layoutInflater)).root
        initData()
        initView()
        return binding.root
    }

    private fun initData() {
        mockRequestRemoteData()
    }

    private fun mockRequestRemoteData() {
        context?.assets?.open("plate_sample_rv.json")?.let {
            InputStreamReader(it).let { reader ->
                mPlateStructure = GsonUtil.fromJson(reader, PlateStructure::class.java)
                reader.close()
            }
            it.close()
        }
    }


    private fun initView() {
        binding.rvContent.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = plate.createRecyclerAdapter(mPlateStructure)

        }
    }
}