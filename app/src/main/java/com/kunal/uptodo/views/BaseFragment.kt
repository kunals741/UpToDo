package com.kunal.uptodo.views

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun activityHandleBack() {
        (requireActivity() as BaseActivity).handleBack()
    }
}