package com.kunal.uptodo.views

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomsheet : BottomSheetDialogFragment() {
    abstract fun pageType() : String
}
