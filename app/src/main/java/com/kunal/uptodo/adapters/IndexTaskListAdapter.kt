package com.kunal.uptodo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.uptodo.databinding.ItemIndexTaskBinding
import com.kunal.uptodo.models.NewTaskModel
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale


class IndexTaskListAdapter(private val onTaskDeleteClick : ((Long) -> Unit)?) : RecyclerView.Adapter<IndexTaskListAdapter.IndexTaskListViewHolder>() {

    private lateinit var binding: ItemIndexTaskBinding
    private var taskList: List<NewTaskModel> = emptyList()

    inner class IndexTaskListViewHolder(val binding: ItemIndexTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newTask: NewTaskModel) = binding.run {
            tvTaskName.text = newTask.title
            tvDeadline.text = newTask.deadlineDate?.let { formatDate(it) }
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

    private fun formatDate(longDate: Long): String {
        val dateObject = Date(longDate)
        val currentDate = Date()

        val deadlineTime = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Format date and time
        val formattedDateTime =
            SimpleDateFormat("dd-MMM", Locale.getDefault()).format(dateObject)

        // Get day part of the date
        val currentDay =
            Calendar.getInstance().apply { time = currentDate }.get(Calendar.DAY_OF_YEAR)
        val targetDay = Calendar.getInstance().apply { time = dateObject }.get(Calendar.DAY_OF_YEAR)

        // Check if the date is today
        return if (currentDay == targetDay) {
            "Today At ${deadlineTime.format(dateObject)}"
        }
        // Check if the date is tomorrow
        else if (currentDay + 1 == targetDay) {
            "Tomorrow At ${deadlineTime.format(dateObject)}"
        } else {
            "$formattedDateTime At ${deadlineTime.format(dateObject)}"
        }
    }

}