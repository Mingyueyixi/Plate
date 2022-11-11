package com.lu.plate.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lu.plate.demo.databinding.ActivityMainBinding
import com.lu.plate.demo.nav.FragmentPageNav


class MainActivity : AppCompatActivity() {
    companion object {
        const val PAGE_DEFAULT = "PAGE_DEFAULT"
        const val PAGE_RV_DEMO = "PAGE_RV_DEMO"
        const val PAGE_SCROLL_DEMO = "PAGE_SCROLL_DEMO"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentPageNav: FragmentPageNav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentPageNav = FragmentPageNav.from(R.id.fragment_container)
            .setLifeActivity(this)
            .setLazyNav(false)
            .create()

        initFragment()
    }

    override fun onBackPressed() {
        if (fragmentPageNav.backFragment(this)) {
            return
        }
        super.onBackPressed()
    }


    fun routeFragment(pageKey: String) {
        fragmentPageNav.showFragment(this, pageKey)
    }

    private fun initFragment() {
        fragmentPageNav.showFragment(this, PAGE_DEFAULT)
    }


}