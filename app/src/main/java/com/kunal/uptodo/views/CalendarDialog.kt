package com.kunal.uptodo.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.databinding.LayoutCalendarBinding
import java.util.Calendar

class CalendarDialog : DialogFragment() {

    private lateinit var binding: LayoutCalendarBinding
    private var onChooseTimeClick: ((Long) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LayoutCalendarBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
        binding.calendarView.minDate = Calendar.getInstance().timeInMillis
        binding.calendarView.dateTextAppearance = R.style.CalenderViewDateCustomText
        binding.footer.btnPositive.text = resources.getString(R.string.choose_time)
        binding.footer.btnNegative.text = resources.getString(R.string.cancel)
        updateDateInCalendar()
        binding.footer.btnPositive.setOnClickListener {
            onChooseTimeClick?.invoke(binding.calendarView.date)
            dismiss()
        }
        binding.footer.btnNegative.setOnClickListener {
            dismiss()
        }
        return builder.create()
    }

    private fun updateDateInCalendar() =
        binding.calendarView.setOnDateChangeListener { calView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val calender: Calendar = Calendar.getInstance()
            calender.set(year, month, dayOfMonth)
            calView.setDate(calender.timeInMillis, true, true)
        }

    override fun getTheme() = R.style.RoundedCornersDialog

    companion object {
        const val TAG = "CalendarDialog"

        fun showCalendarDialog(
            source: String,
            fragmentManager: FragmentManager,
            actionListener: ((Long) -> Unit)? = null
        ) = CalendarDialog().apply {
            arguments?.putString(IntentKeyConstants.SOURCE, source)
            onChooseTimeClick = actionListener
        }.show(fragmentManager, TAG)
    }
}