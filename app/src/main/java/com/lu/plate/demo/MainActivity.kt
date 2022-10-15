package com.lu.plate.demo

import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lu.plate.demo.R
import com.lu.plate.demo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pageStateAdapter: PageStateHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pageStateAdapter = PageStateHolder()
        initFragment()
    }

    override fun onBackPressed() {
        var currIndex = pageStateAdapter.fragmentIndex
        if (currIndex > 0) {
            switchFragment(currIndex - 1)
            return
        }
        super.onBackPressed()
    }

    fun switchFragment(position: Int) {
        supportFragmentManager.beginTransaction().also {
            it.add(R.id.fragment_container, pageStateAdapter.getFragment(position))
            it.show(pageStateAdapter.getFragment(position))
        }.commitNow()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.fragment_container, pageStateAdapter.getFragment(0))
        }.commitNow()
    }

    class PageStateHolder {
        var fragmentIndex = 0
        var fragments: SparseArray<Fragment?> = SparseArray()

        fun getFragment(position: Int): Fragment {
            var fragment = fragments[position]
            if (fragment == null) {
                fragment = when (position) {
                    0 -> MainFragment()
                    else -> MainFragment()
                }
                fragments[position] = fragment
            }
            return fragment
        }

    }

}