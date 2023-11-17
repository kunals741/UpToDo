package com.kunal.uptodo.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.databinding.LayoutChooseTimeBinding
import java.util.Calendar

class ChooseTimeDialog : DialogFragment() {
    private lateinit var binding: LayoutChooseTimeBinding
    private var onSaveClicked: ((String, String, String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LayoutChooseTimeBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = AlertDialog.Builder(requireContext())
            .setView(binding.root)
        val calendar: Calendar = Calendar.getInstance()
        val hour12hrs = calendar[Calendar.HOUR]
        val minutes = calendar[Calendar.MINUTE]
        val currentTimeInMinutes = hour12hrs * 60 + minutes
        binding.tpMain.setIs24HourView(false)
        binding.footer.btnPositive.setOnClickListener {
            val amPm = if (binding.tpMain.hour < 12) "AM" else "PM"
            val selectedTimeInMinutes = binding.tpMain.hour * 60 + binding.tpMain.minute
            onSaveClicked?.invoke(binding.tpMain.hour.toString(), binding.tpMain.minute.toString(), amPm)
            dismiss()
            //todo handle previous time :
//            if (selectedTimeInMinutes < currentTimeInMinutes) {
//                val message = "Please select a time later than the current time."
//                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
//            } else {
//                onSaveClicked?.invoke(binding.tpMain.hour.toString(), binding.tpMain.minute.toString(), amPm)
//                dismiss()
//            }
        }
        binding.footer.btnNegative.setOnClickListener {
            dismiss()
        }
        return dialog.create()
    }

    override fun getTheme() = R.style.RoundedCornersDialog

    companion object {
        private const val TAG = "ChooseTimeDialog"

        fun showChooseTimeDialog(
            source: String,
            fragmentManager: FragmentManager,
            onSaveClicked: ((hour: String, minute: String, timeText: String) -> Unit)?
        ) = ChooseTimeDialog().apply {
            arguments?.putString(IntentKeyConstants.SOURCE, source)
            this.onSaveClicked = onSaveClicked
        }.show(fragmentManager, TAG)
    }
}