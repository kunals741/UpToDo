package com.kunal.uptodo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kunal.uptodo.models.NewTaskModel

class TaskViewModel : ViewModel() {
    private val taskDeadline by lazy { MutableLiveData<Long>() }
    private val taskList = ArrayList<NewTaskModel>() //todo later move to firestore
    private val _taskListLiveData by lazy { MutableLiveData<List<NewTaskModel>>() }
    val taskListLiveData: LiveData<List<NewTaskModel>> = _taskListLiveData

    fun addNewTask(newTask: NewTaskModel) {
        taskList.add(newTask)
        _taskListLiveData.postValue(taskList)
    }

    fun updateTaskList(list: List<NewTaskModel>) {
        taskList.clear()
        _taskListLiveData.postValue(list)
    }

}