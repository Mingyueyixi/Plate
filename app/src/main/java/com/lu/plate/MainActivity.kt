package com.lu.plate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lu.plate.databinding.ActivityMainBinding
import com.lu.plate.recycler.data.ContentWrap
import com.lu.plate.recycler.data.PlateStructure
import com.lu.plate.recycler.data.ShapeBackground
import com.lu.plate.recycler.data.Style
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
                ContentWrap(
                    1,
                    Style(background = ShapeBackground(color = "#FFAA3399"))
                )
            )
            contents.add(ContentWrap(1))
            contents.add(ContentWrap(1))
            contents.add(ContentWrap(1))
        }

    }

}