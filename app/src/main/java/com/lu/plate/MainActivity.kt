package com.lu.plate

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lu.plate.databinding.ActivityMainBinding
import com.lu.plate.recycler.data.ComponentModel
import com.lu.plate.recycler.data.PlateStructure
import com.lu.plate.recycler.component.CardComponent
import com.lu.plate.recycler.data.ShapeBackground
import com.lu.plate.recycler.data.Style

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var plateManager: PlateManager = PlateManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        PlateManager.doInit(application)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var data = PlateStructure().apply {
            components.add(
                ComponentModel(
                    1,
                    Style(background = ShapeBackground(color = "#FFAA3399"))
                )
            )
            components.add(ComponentModel(1))
            components.add(ComponentModel(1))
            components.add(ComponentModel(1))
        }

        plateManager.register(CardComponent(1))
        binding.rvContent.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvContent.adapter = plateManager.createRecyclerAdapter(data)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}