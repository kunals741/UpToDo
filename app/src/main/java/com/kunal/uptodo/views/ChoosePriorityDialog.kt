package com.kunal.uptodo.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.kunal.uptodo.R
import com.kunal.uptodo.adapters.PriorityAdapter
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.databinding.LayoutChoosePriorityBinding

class ChoosePriorityDialog : DialogFragment() {
    private lateinit var binding: LayoutChoosePriorityBinding
    private var onSaveClicked: ((Int) -> Unit)? = null
    private var priorityNumberSelected = 1

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LayoutChoosePriorityBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)

        binding.rvPriorities.adapter = PriorityAdapter(10) { priorityNumber ->
            priorityNumberSelected = priorityNumber
        }
        binding.footer.btnPositive.apply {
            text = context.getString(R.string.save)
            setOnClickListener {
                onSaveClicked?.invoke(priorityNumberSelected)
                dismiss()
            }
        }
        binding.footer.btnNegative.apply {
            setOnClickListener {
                dismiss()
            }
        }
        return builder.create()
    }

    companion object {
        private const val TAG = "ChoosePriorityDialog"

        fun showChoosePriorityDialog(
            source: String,
            fragmentManager: FragmentManager,
            onSaveClicked: ((Int) -> Unit)? = null
        ) = ChoosePriorityDialog().apply {
            arguments?.putString(IntentKeyConstants.SOURCE, source)
            this.onSaveClicked = onSaveClicked
        }.show(fragmentManager, TAG)
    }
}