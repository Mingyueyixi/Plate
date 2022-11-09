package com.lu.plate.demo.base

import androidx.viewbinding.ViewBinding

open class BindingFragment<VB : ViewBinding> : BaseFragment() {
    private var _binding: VB? = null
    open val binding: VB get() = _binding!!

    fun viewBinding(vb: VB): VB {
        _binding = vb
        return vb
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

