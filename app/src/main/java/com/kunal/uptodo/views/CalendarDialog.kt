package com.kunal.uptodo.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.kunal.uptodo.R
import com.kunal.uptodo.databinding.LayoutCalendarBinding
import java.util.Calendar

class CalendarDialog : DialogFragment() {

    private lateinit var binding: LayoutCalendarBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LayoutCalendarBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireActivity())
            .setView(binding.root)

        binding.calendarView.minDate = Calendar.getInstance().timeInMillis
        binding.calendarView.dateTextAppearance = R.style.CalenderViewDateCustomText
        return builder.create()
    }

    companion object {
        const val TAG = "CalendarDialog"
    }
}