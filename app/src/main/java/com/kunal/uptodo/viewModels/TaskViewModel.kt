package com.kunal.uptodo.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val taskDeadline by lazy {MutableLiveData<Long>()}


//    fun formatEpochDate(epochTime: Long): String {
//        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//        val date = Date(epochTime)
//        return sdf.format(date)
//    }
}