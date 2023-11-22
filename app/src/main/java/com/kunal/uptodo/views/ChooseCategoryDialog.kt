package com.kunal.uptodo.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.kunal.uptodo.R
import com.kunal.uptodo.adapters.CategoryAdapter
import com.kunal.uptodo.adapters.GridSpacingItemDecoration
import com.kunal.uptodo.constants.CategoryType
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.data.CategoryList
import com.kunal.uptodo.databinding.LayoutChooseCategoryBinding

class ChooseCategoryDialog : DialogFragment() {
    private lateinit var binding: LayoutChooseCategoryBinding
    private var onAddCategoryClicked: ((CategoryType) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LayoutChooseCategoryBinding.inflate(LayoutInflater.from(requireContext()))

        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)
        val horizontalSpacingInPixels = resources.getDimensionPixelSize(R.dimen.dim_50dp)
        val verticalSpacingInPixels = resources.getDimensionPixelSize(R.dimen.dim_16dp)
        binding.rvCategories.adapter = CategoryAdapter(CategoryList().getCategoryList()) {
            when (it) {
                CategoryType.CREATE_NEW -> {
                    CreateNewCategoryActivity.launchCreateNewCategory(PageName.ChooseCategoryDialog, requireActivity())
                }
            }
        }
        binding.rvCategories.addItemDecoration(GridSpacingItemDecoration(horizontalSpacingInPixels, verticalSpacingInPixels, 3))

        return builder.create()
    }

    companion object {
        private const val TAG = "ChooseCategoryDialog"

        fun showChooseCategoryDialog(
            source: String,
            fragmentManager: FragmentManager,
            onAddCategoryClicked: ((CategoryType) -> Unit)?
        ) = ChooseCategoryDialog().apply {
            arguments?.putString(IntentKeyConstants.SOURCE, source)
            this.onAddCategoryClicked = onAddCategoryClicked
        }.show(fragmentManager, TAG)
    }
}