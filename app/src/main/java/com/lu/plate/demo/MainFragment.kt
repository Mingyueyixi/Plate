package com.lu.plate.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.lu.plate.Plate
import com.lu.plate.data.Content
import com.lu.plate.data.PlateStructure
import com.lu.plate.data.Style
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.demo.databinding.FragmentRvDemoBinding
import com.lu.plate.demo.route.AppLinkRouter
import com.lu.plate.demo.route.RouteLinkResolver
import com.lu.plate.template.ImageBarPanelRVTemplate
import com.lu.plate.util.GsonUtil
import com.lu.plate.util.JsonObjectKotlin
import com.lu.plate.util.optString

class MainFragment : Fragment() {
    private lateinit var mPlateData: PlateStructure

    private lateinit var binding: FragmentRvDemoBinding
    private val plate: Plate by lazy {
        Plate().also {
            it.register(ImageBarPanelRVTemplate(it, 1))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            Plate.doInit(it)
        }
        binding = FragmentRvDemoBinding.inflate(layoutInflater)
        initData()
        initView()
        return binding.root
    }

    private fun initView() {
        var atapter = plate.createRecyclerAdapter(mPlateData).also {
            binding.rvContent.adapter = it
            binding.rvContent.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        atapter.setOnClickListener { adapter, v, p ->
            var content = adapter.getItem(p)
            content?.props?.optString("clickLink").let {
                //跳转链接
                activity?.let { act ->
                    AppLinkRouter.route(act, it)
                }
            }

        }

    }

    fun initData() {
        mPlateData = PlateStructure()
        arrayListOf(
            Content(
                1,
                Style(
                    background = ShapeBackground(solid = ShapeBackground.Solid("#FFAA3399")),
                    marginBottom = "100"
                ),
                props = JsonObjectKotlin.from(
                    """{
                            "clickLink": "${RouteLinkResolver.buildAppWebLink("https://www.biying.com")}",
                             "cardViewEx": {
                                    "cardCornerRadius": "8dp",
                                    "cardElevation": "2dp",
                                    "strokeWidth": "10dp",
                                    "cardBackgroundColor": "#FFeebbcc"
                                },
                             "title": "必应",
                             "subTitle": "点击访问必应首页",
                             "img": "https://s2.cn.bing.net/th?id=OJ.yRwa0z300b9YpA&w=120&h=160&c=8&rs=1&pid=academic"
                        }""".trimIndent()
                )
            ),
            Content(
                1, Style(marginTop = "0", marginBottom = "10dp"), props = JsonObjectKotlin.from(
                    """{
                    "clickLink": "${RouteLinkResolver.buildAppMainLink(MainActivity.PAGE_RV_DEMO)}",
                    "title": "App内跳转",
                    "subTitle": "visit RVDemoFragment page",
                    "img": "https://www.baidu.com/more/img/anquan.png"
                }""".trimIndent()
                )
            ),
            Content(
                1, Style(marginTop = "0", marginBottom = "10dp"), props = JsonObjectKotlin.from(
                    """{
                    "clickLink": "${RouteLinkResolver.buildAppMainLink(MainActivity.PAGE_SCROLL_DEMO)}",
                    "title": "App内跳转",
                    "subTitle": "visit ScrollDemoFragment page",
                    "img": "https://p4.ssl.qhimg.com/t01bcbe50279dfd6f41.png"
                }""".trimIndent()
                )
            ),
            Content(1, Style(marginTop = "0", marginBottom = "0")),
            Content(1, Style(marginTop = "0", marginBottom = "0")),
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
        ).let {
            it.forEachIndexed { index, item ->
                item.id = index
            }
            mPlateData.contents.addAll(it)
        }

        LogUtils.w(">>>", GsonUtil.getPrettyGson().toJson(mPlateData))
    }

}