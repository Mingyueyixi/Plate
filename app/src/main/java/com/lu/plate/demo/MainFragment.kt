package com.lu.plate.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lu.plate.PlateManager
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.data.Style
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.demo.databinding.FragmentRvDemoBinding
import com.lu.plate.template.CardRVTemplate

class MainFragment : Fragment() {
    private lateinit var mPlateData: PlateStructure

    private lateinit var binding: FragmentRvDemoBinding
    private val plateManager: PlateManager by lazy {
        PlateManager().register(CardRVTemplate(1))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            PlateManager.doInit(it)
        }
        binding = FragmentRvDemoBinding.inflate(layoutInflater)
        initData()
        initView()
        return binding.root
    }

    private fun initView() {
        binding.rvContent.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = plateManager.createRecyclerAdapter(mPlateData)
        }
    }

    fun initData() {
        mPlateData = PlateStructure().apply {
            contents.add(
                Content(
                    1,
                    Style(
                        background = ShapeBackground(solid = ShapeBackground.Solid("#FFAA3399")),
                        marginBottom = "100"
                    )
                )
            )
            contents.add(Content(1, Style(marginTop = "0", marginBottom = "100 dp")))
            contents.add(Content(1, Style(marginTop = "0", marginBottom = "0")))
            contents.add(Content(1, Style(marginTop = "0", marginBottom = "0")))
            contents.add(
                Content(
                    1,
                    Style(
                        marginTop = "0",
                        marginBottom = "0",
                        paddingBottom = "50dp",
                        background = ShapeBackground(
                            solid = ShapeBackground.Solid("#FF00ee33"),
                            stroke = ShapeBackground.Stroke(width = "10dp"),
                        )
                    )
                )
            )
        }
    }

}