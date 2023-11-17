package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.BottomsheetAddTaskBinding

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
    }

    private fun showCalendarPopUp() {
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//        val constraintsBuilder = CalendarConstraints.Builder()
//            .setStart(calendar.timeInMillis)
//            .setValidator(DateValidatorPointForward.now())
//            .build()
//
//        val dataPicker = MaterialDatePicker.Builder.datePicker()
//            .setTitleText("Select date")
//            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
//            .setCalendarConstraints(constraintsBuilder)
//            .build()
//
//            dataPicker.addOnPositiveButtonClickListener {
//                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_LONG).show()
//            }
//                dataPicker.show(childFragmentManager, TAG)
//
        CalendarDialog().show(childFragmentManager, CalendarDialog.TAG)
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