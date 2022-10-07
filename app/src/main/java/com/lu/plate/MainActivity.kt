package com.lu.plate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lu.plate.databinding.ActivityMainBinding
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.data.Style
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.template.CardRVTemplate

class MainActivity : AppCompatActivity() {
    private lateinit var mPlateData: PlateStructure

    private lateinit var binding: ActivityMainBinding
    private val plateManager: PlateManager by lazy {
        PlateManager().apply {
            register(CardRVTemplate(1))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        PlateManager.doInit(application)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
    }

    private fun initView() {
        binding.rvContent.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvContent.adapter = plateManager.createRecyclerAdapter(mPlateData)
    }

    fun initData() {
        mPlateData = PlateStructure().apply {
            contents.add(
                Content(
                    1,
                    Style(background = ShapeBackground(solid = ShapeBackground.Solid("#FFAA3399")), marginBottom = "100")
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