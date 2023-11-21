package com.kunal.uptodo.views

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.BottomsheetAddTaskBinding
import java.util.Date

class AddTaskBottomsheet : BaseBottomsheet() {
    override fun pageType() = PageName.AddTaskBottomsheet

    private lateinit var binding: BottomsheetAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBackgroundDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetAddTaskBinding.inflate(inflater, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = binding.run {
        ivDeadline.setOnClickListener {
            showCalendarPopUp()
        }
        ivPriority.setOnClickListener {
            showChoosePriorityPopUp()
        }
        ivCategory.setOnClickListener {
            showChooseCategoryPopUp()
        }
    }

    private fun showCalendarPopUp() {
        CalendarDialog.showCalendarDialog(
            source = PageName.AddTaskBottomsheet,
            childFragmentManager
        ) { dateMillis ->
            // Create Date object from milliseconds.
            val date = Date(dateMillis)

            // Get Date values and created formatted string date to show in Toast.
            val selectedDayOfWeek = DateFormat.format("EEEE", date) as String // Monday
            val selectedDay = DateFormat.format("dd", date) as String // 05
            val selectedMonthString = DateFormat.format("MMM", date) as String // Jul
            val selectedMonthNumber = DateFormat.format("MM", date) as String // 07
            val selectedYear = DateFormat.format("yyyy", date) as String // 2021

            val strFormattedSelectedDate = StringBuilder()
                .append(selectedDay)
                .append(" ")
                .append(selectedMonthNumber.toInt())
                .append(" ")
                .append(selectedYear)
                .append(" ")
                .append(selectedDayOfWeek)
            Toast.makeText(requireContext(), strFormattedSelectedDate, Toast.LENGTH_SHORT).show()

            ChooseTimeDialog.showChooseTimeDialog(
                PageName.AddTaskBottomsheet,
                childFragmentManager
            ) { hour, minute, timeText ->
                val time = StringBuilder()
                    .append(hour)
                    .append(" : ")
                    .append(minute)
                    .append(" ")
                    .append(timeText)
                Toast.makeText(requireContext(), time, Toast.LENGTH_SHORT).show()
            }
            //todo save this deadline
            //todo also handle the case when he clicks cancel button
        }
    }

    private fun showChoosePriorityPopUp() {
        ChoosePriorityDialog.showChoosePriorityDialog(
            PageName.AddTaskBottomsheet,
            childFragmentManager
        ){
            //todo
        }
    }

    private fun showChooseCategoryPopUp(){
        ChooseCategoryDialog.showChooseCategoryDialog(
            PageName.AddTaskBottomsheet,
            childFragmentManager
        ){
            //todo
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            source: String
        ) = AddTaskBottomsheet().apply {
            arguments = Bundle().apply {
                putString(IntentKeyConstants.SOURCE, source)
            }
        }

        const val TAG = "AddTaskBottomsheet"
    }
}