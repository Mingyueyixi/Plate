package com.lu.plate.demo.nav

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.lu.plate.demo.*

class FragmentPageNav private constructor(
    private val layoutResId: Int,
    var isLazyNav: Boolean = false
) :
    LifecycleEventObserver {
    val fragmentList = arrayListOf<Fragment?>()

    companion object {
        fun from(layoutResId: Int): Builder {
            return Builder(layoutResId)
        }
    }

    class Builder(
        private var layoutResId: Int,
        private var lifeActivity: FragmentActivity? = null,
        private var isLazyNav: Boolean = false,
    ) {

        fun setLazyNav(isLazyNav: Boolean): Builder {
            this.isLazyNav = isLazyNav
            return this
        }

        fun setLifeActivity(activity: FragmentActivity): Builder {
            lifeActivity = activity
            return this
        }

        fun create(): FragmentPageNav {
            return FragmentPageNav(layoutResId, isLazyNav).also {
                lifeActivity?.lifecycle?.addObserver(it)
            }
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
                MainActivity.PAGE_SCROLL_DEMO -> SVDemoFragment()
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
            if (isLazyNav) {
                getCurrFragment()?.let { curr ->
                    it.hide(curr)
                }
                if (frag.isAdded) {
                    it.show(frag)
                    fragmentList.remove(frag)
                } else {
                    it.add(layoutResId, frag, pageKey)
                }
            } else {
                if (frag.isAdded) {
                    it.show(frag)
                    fragmentList.remove(frag)
                } else {
                    it.replace(layoutResId, frag, pageKey)
                }
            }
            fragmentList.add(frag)
        }.commitNow()
    }

    fun backFragment(act: AppCompatActivity): Boolean {
        act.supportFragmentManager.beginTransaction().let {
            fragmentList.removeLastOrNull()?.let { last ->
                it.remove(last)
            }
            val backFrag = fragmentList.lastOrNull() ?: return false
            if (isLazyNav) {
                it.show(backFrag)
            } else {
                it.replace(layoutResId, backFrag)
            }
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