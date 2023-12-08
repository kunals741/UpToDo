package com.kunal.uptodo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.kunal.uptodo.adapters.IndexTaskListAdapter
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentIndexBinding
import com.kunal.uptodo.models.NewTaskModel

class IndexFragment : BaseFragment() {

    private lateinit var binding: FragmentIndexBinding
    private var newTask: NewTaskModel? = null
    private var taskList = mutableListOf<NewTaskModel>() //todo later change to save the data ( firestore maybe)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndexBinding.inflate(inflater, container, false)
        newTask = arguments?.getParcelable(IntentKeyConstants.NEW_TASK_MODEL)
        newTask?.let { taskList.add(it) }
        if ( newTask == null || taskList.isEmpty()) {
            binding.ivChecklist.isVisible = true
            binding.tvQuestion.isVisible = true
            binding.tvTapIcon.isVisible = true
            binding.rvTasks.isVisible = false
        } else {
            binding.ivChecklist.isVisible = false
            binding.tvQuestion.isVisible = false
            binding.tvTapIcon.isVisible = false
            binding.rvTasks.isVisible = true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
    }

    private fun initView() = binding.run {
        rvTasks.adapter = IndexTaskListAdapter(taskList)
    }

    private fun initListeners() = binding.run {

    }

    override fun getPageName() = PageName.IndexFragment

    companion object {
        @JvmStatic
        fun newInstance(
            newTask: NewTaskModel?
        ) = IndexFragment().apply {
            arguments = Bundle().apply {
                putParcelable(IntentKeyConstants.NEW_TASK_MODEL, newTask)
            }

        }

        const val INDEX = "Index"
    }
}