package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentCalendarBinding


class CalendarFragment : BaseFragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = binding.run {
        //todo later
    }

    override fun getPageName() = PageName.CalendarFragment

    companion object {
        @JvmStatic
        fun newInstance() = CalendarFragment()
        const val CALENDAR = "Calendar"
    }

}