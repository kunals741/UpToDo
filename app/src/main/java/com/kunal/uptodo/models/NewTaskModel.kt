package com.kunal.uptodo.models

data class NewTaskModel(
    val title: String?,
    val description: String?,
    val deadlineDate: Long?,
    val selectedTime: SelectedTime?,
    val category: String?,
    val priorityNumber: Int?
) {
    data class SelectedTime(
        val hour: String,
        val minute: String,
        val timeText: String
    )
}