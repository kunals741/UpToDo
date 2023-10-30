package com.kunal.uptodo.views

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected fun activityHandleBack() {
        (requireActivity() as BaseActivity).handleBack()
    }

    abstract fun getPageName() : String
}