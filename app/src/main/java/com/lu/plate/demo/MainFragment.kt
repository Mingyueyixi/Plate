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
import com.lu.plate.data.Element
import com.lu.plate.data.PlateStructure
import com.lu.plate.data.Style
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.demo.databinding.FragmentRvDemoBinding
import com.lu.plate.demo.route.AppLinkRouter
import com.lu.plate.demo.route.RouteLinkResolver
import com.lu.plate.util.GsonUtil
import com.lu.plate.util.JsonObjectKotlin
import com.lu.plate.util.optString

class MainFragment : Fragment() {
    private lateinit var mPlateData: PlateStructure

    private lateinit var binding: FragmentRvDemoBinding
    private val plate: Plate by lazy {
        PlateManager.createWithCommon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentRvDemoBinding.inflate(layoutInflater)
        initData()
        initView()
        return binding.root
    }

    private fun initView() {
        val atapter = plate.createRecyclerAdapter(mPlateData).also {
            binding.rvContent.adapter = it
            binding.rvContent.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        atapter.setOnClickListener { adapter, _, p ->
            val content = adapter.getItem(p)
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
                3,
                Style(marginTop = "0", marginBottom = "16dp"),
                elements = arrayListOf(
                    Element(
                        props = JsonObjectKotlin.from(
                            """{
                        "img": "https://ts3.cn.mm.bing.net/th?id=OIP-C.mXMQFcfiRz13HB2jLrTUPQHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                    }""".trimIndent()
                        )
                    ),
                    Element(
                        props = JsonObjectKotlin.from(
                            """{
                        "img": "https://ts3.cn.mm.bing.net/th?id=OIP-C.l0UBlPzbItR94T6REJQ79wHaJQ&w=223&h=279&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                    }""".trimIndent()
                        )
                    ),
                    Element(
                        props = JsonObjectKotlin.from(
                            """{
                        "img": "https://ts3.cn.mm.bing.net/th?id=OIP-C.G3neuy5XDKCfUKSSrC2FSAHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                    }""".trimIndent()
                        )
                    ),
                    Element(
                        props = JsonObjectKotlin.from(
                            """{
                        "img": "https://tse1-mm.cn.bing.net/th/id/OIP-C.ND5vWa5UAXCp289N7lCUIwHaEo?w=297&h=186&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                    }""".trimIndent()
                        )
                    ),

                    )
            ),
            Content(
                1, Style(
                    background = ShapeBackground(solid = ShapeBackground.Solid("#FFAA3399")),
                    marginBottom = "100"
                ), props = JsonObjectKotlin.from(
                    """{
                            "clickLink": "${RouteLinkResolver.buildAppWebLink("https://www.biying.com")}",
                             "cardViewEx": {
                                    "cardCornerRadius": "8dp",
                                    "cardElevation": "2dp",
                                    "strokeWidth": "16dp",
                                    "cardBackgroundColor": "#FFeebbcc"
                                },
                             "title": "必应",
                             "subTitle": "点击访问必应首页",
                             "img": "https://s2.cn.bing.net/th?id=OJ.yRwa0z300b9YpA&w=120&h=160&c=8&rs=1&pid=academic"
                        }""".trimIndent()
                )
            ),
            Content(
                1, Style(marginTop = "0", marginBottom = "16dp"), props = JsonObjectKotlin.from(
                    """{
                    "clickLink": "${RouteLinkResolver.buildAppMainLink(MainActivity.PAGE_RV_DEMO)}",
                    "title": "App内跳转",
                    "subTitle": "visit RVDemoFragment page",
                    "img": "https://www.baidu.com/more/img/anquan.png"
                }""".trimIndent()
                )
            ),
            Content(
                1, Style(marginTop = "0", marginBottom = "16dp"), props = JsonObjectKotlin.from(
                    """{
                    "clickLink": "${RouteLinkResolver.buildAppMainLink(MainActivity.PAGE_SCROLL_DEMO)}",
                    "title": "App内跳转",
                    "subTitle": "visit ScrollDemoFragment page",
                    "img": "https://p4.ssl.qhimg.com/t01bcbe50279dfd6f41.png"
                }""".trimIndent()
                )
            ),
            Content(1, Style(marginTop = "0", marginBottom = "16dp")),
            Content(
                1, Style(
                    marginTop = "0",
                    marginBottom = "0",
                    paddingBottom = "16dp",
                    background = ShapeBackground(
                        solid = ShapeBackground.Solid("#FF00ee33"),
                        stroke = ShapeBackground.Stroke(width = "16dp"),
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