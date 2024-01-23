package com.kunal.uptodo.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.ActivityHomeBinding
import com.kunal.uptodo.models.NewTaskModel
import com.kunal.uptodo.shared_pref.UserSession
import com.kunal.uptodo.viewModels.TaskViewModel


class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private val userSession: UserSession by lazy { UserSession(this) }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val tasksCollectionRef by lazy {
        db.collection("users").document(userSession.getUserId()).collection(
            "tasks"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseAuth.getInstance().currentUser?.uid?.let { userSession.setUserId(it) }
        showFragment(IndexFragment.newInstance(), IndexFragment.INDEX)
        setAddTaskBottomsheetListener()
        setBottomNavigationListener()
        getUserTasksFromFirestore()
        setTaskChangeListener()
    }


    private fun setBottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showFragment(IndexFragment.newInstance(), IndexFragment.INDEX)
                    true
                }

                R.id.navigation_calendar -> {
                    showFragment(CalendarFragment.newInstance(), CalendarFragment.CALENDAR)
                    true
                }

                R.id.navigation_focus -> {
                    showFragment(FocusFragment.newInstance(), FocusFragment.FOCUS_MODE)
                    true
                }

                R.id.navigation_profile -> {
                    showFragment(ProfileFragment.newInstance(), ProfileFragment.PROFILE)
                    true
                }

                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment, screenName: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rootView, fragment)
            .commit()
        binding.tvFragmentName.text = screenName
    }

    private fun setAddTaskBottomsheetListener() {
        binding.ivPlusBtn.setOnClickListener {
            AddTaskBottomsheet.newInstance(pageType(), supportFragmentManager) {
                addTaskToFirestore(it)
                binding.bottomNavigation.selectedItemId = R.id.navigation_home //todo later improve the lag
            }
        }
    }

    private fun addTaskToFirestore(newTask: NewTaskModel) {
        tasksCollectionRef.add(newTask)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Task added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error adding task", exception)
            }
    }

    private fun getUserTasksFromFirestore() {
        tasksCollectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                val tasks = querySnapshot.toObjects(NewTaskModel::class.java)
                taskViewModel.updateTaskList(tasks)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error adding task", exception)
            }
    }

    private fun setTaskChangeListener() {
        tasksCollectionRef.addSnapshotListener { querySnapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            val tasks = querySnapshot?.toObjects(NewTaskModel::class.java)
            if (tasks != null) {
                taskViewModel.updateTaskList(tasks)
            }
        }
    }

    override fun handleBack() {

    }

    override fun pageType(): String = PageName.HomeActivity


    companion object {
        const val TAG = "HomeActivity"

        @JvmStatic
        fun startHomeActivity(
            source: String,
            context: Context
        ) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(IntentKeyConstants.SOURCE, source)
            }
            context.startActivity(intent)
        }
    }
}