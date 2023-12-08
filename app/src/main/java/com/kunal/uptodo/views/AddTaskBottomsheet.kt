package com.kunal.uptodo.views

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.CategoryType
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.BottomsheetAddTaskBinding
import com.kunal.uptodo.models.NewTaskModel
import java.util.Date

class AddTaskBottomsheet : BaseBottomsheet() {
    override fun pageType() = PageName.AddTaskBottomsheet

    private lateinit var binding: BottomsheetAddTaskBinding

    private var onNewTaskClick: ((NewTaskModel) -> Unit)? = null
    private var deadline: Long? = null
    private var selectedTime: NewTaskModel.SelectedTime? = null
    private var category: CategoryType? = null
    private var priorityNumber: Int? = null

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
        ivAddTask.setOnClickListener {
            onNewTaskClick?.invoke(
                NewTaskModel(
                    etTask.text?.toString(),
                    etDescription.text?.toString(),
                    deadline,
                    selectedTime,
                    category,
                    priorityNumber
                )
            )
            dismiss()
        }
    }

    private fun showCalendarPopUp() {
        CalendarDialog.showCalendarDialog(
            PageName.AddTaskBottomsheet,
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

            deadline = dateMillis

            showChooseTimeDialog()
        }
    }

    private fun showChooseTimeDialog() {
        ChooseTimeDialog.showChooseTimeDialog(
            PageName.AddTaskBottomsheet,
            childFragmentManager
        ) { hour, minute, timeText ->
            selectedTime = NewTaskModel.SelectedTime(
                hour,
                minute,
                timeText
            )

            //todo cancel button
        }
    }

    private fun showChoosePriorityPopUp() {
        ChoosePriorityDialog.showChoosePriorityDialog(
            PageName.AddTaskBottomsheet,
            childFragmentManager
        ) {
            priorityNumber = it
        }
    }

    private fun showChooseCategoryPopUp() {
        ChooseCategoryDialog.showChooseCategoryDialog(
            PageName.AddTaskBottomsheet,
            childFragmentManager
        ) {
            category = it
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            source: String,
            fragmentManager: FragmentManager,
            onAddTaskClick: ((NewTaskModel) -> Unit)?
        ) = AddTaskBottomsheet().apply {
            arguments = Bundle().apply {
                putString(IntentKeyConstants.SOURCE, source)
                onNewTaskClick = onAddTaskClick
            }
        }.show(fragmentManager, TAG)

        private const val TAG = "AddTaskBottomsheet"
    }
}