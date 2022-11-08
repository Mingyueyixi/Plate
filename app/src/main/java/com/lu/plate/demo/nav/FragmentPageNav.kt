package com.lu.plate.demo.nav

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.lu.plate.demo.*

class FragmentPageNav private constructor() : LifecycleEventObserver {
    val fragmentList = arrayListOf<Fragment?>()

    companion object {
        fun lifeCreate(act: FragmentActivity): FragmentPageNav {
            val ret = FragmentPageNav()
            act.lifecycle.addObserver(ret)
            return ret
        }

        fun create(): FragmentPageNav {
            return FragmentPageNav()
        }
    }

    private fun getFragment(tag: String): Fragment {
        var frag: Fragment? = fragmentList.firstOrNull {
            tag == it?.tag
        }
        if (frag == null) {
            frag = when (tag) {
                MainActivity.PAGE_DEFAULT -> MainFragment()
                MainActivity.PAGE_RV_DEMO -> RVDemoFragment()
                MainActivity.PAGE_SCROLL_DEMO -> ScrollDemoFragment()
                else -> MainFragment()
            }
        }
        return frag
    }

    private fun getCurrFragment(): Fragment? {
        return fragmentList.lastOrNull()
    }

    fun showFragment(act: AppCompatActivity, pageKey: String) {
        act.supportFragmentManager.beginTransaction().also {
            val frag = getFragment(pageKey)
            getCurrFragment()?.let { curr ->
                it.hide(curr)
            }
            if (frag.isAdded) {
                it.show(frag)
                fragmentList.remove(frag)
            } else {
                it.add(R.id.fragment_container, frag, pageKey)
            }
            fragmentList.add(frag)
        }.commitNow()
    }

    fun backFragment(act: AppCompatActivity): Boolean {
        return act.supportFragmentManager.beginTransaction().let {
            fragmentList.removeLastOrNull()?.let { last ->
                it.remove(last)
            }
            val backFrag = fragmentList.lastOrNull() ?: return false
            it.show(backFrag)
            it.commitNow()
            return true
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            fragmentList.clear()
        }
    }
}