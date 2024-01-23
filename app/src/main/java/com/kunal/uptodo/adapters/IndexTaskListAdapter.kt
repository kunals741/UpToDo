package com.kunal.uptodo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.uptodo.databinding.ItemIndexTaskBinding
import com.kunal.uptodo.models.NewTaskModel


class IndexTaskListAdapter(private val onTaskDeleteClick : ((Long) -> Unit)?) : RecyclerView.Adapter<IndexTaskListAdapter.IndexTaskListViewHolder>() {

    private lateinit var binding: ItemIndexTaskBinding
    private var taskList: List<NewTaskModel> = emptyList()

    inner class IndexTaskListViewHolder(val binding: ItemIndexTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newTask: NewTaskModel) = binding.run {
            tvTaskName.text = newTask.title
            tvDeadline.text = newTask.deadlineDate.toString()
            radioButton.isChecked = false
            radioButton.setOnClickListener{
                onTaskDeleteClick?.invoke(newTask.timeCreated)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IndexTaskListViewHolder {
        binding = ItemIndexTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndexTaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndexTaskListViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount() = taskList.size

    fun setTaskList(taskList: List<NewTaskModel>) {
        this.taskList = taskList
        notifyDataSetChanged() //todo optimized this later
    }
}