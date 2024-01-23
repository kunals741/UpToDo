package com.kunal.uptodo.models

import android.os.Parcelable
import com.kunal.uptodo.constants.CategoryType
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NewTaskModel(
    val title: String?,
    val description: String?,
    val deadlineDate: Long?,
    val selectedTime: SelectedTime?,
    val category: @RawValue CategoryType?,
    val priorityNumber: Int?,
    val timeCreated : Long?
) : Parcelable {

    // No-argument constructor: ( neeeded for deseriazling data from firestore)
    constructor() : this(null, null, null, null, null, null, null)

    @Parcelize
    data class SelectedTime(
        val hour: String,
        val minute: String,
        val timeText: String
    ) : Parcelable
}