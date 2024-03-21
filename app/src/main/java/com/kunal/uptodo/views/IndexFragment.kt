package com.kunal.uptodo.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.kunal.uptodo.adapters.IndexTaskListAdapter
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.FragmentIndexBinding
import com.kunal.uptodo.shared_pref.UserSession
import com.kunal.uptodo.viewModels.TaskViewModel

class IndexFragment : BaseFragment() {

    private lateinit var binding: FragmentIndexBinding
    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var adapter: IndexTaskListAdapter
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val userSession: UserSession by lazy { UserSession(requireContext()) }
    private val tasksCollectionRef by lazy {
        db.collection("users").document(userSession.getUserId()).collection(
            "tasks"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() = binding.run {
        adapter = IndexTaskListAdapter {
            deleteTaskFromFirestore(it)
        }
        rvTasks.adapter = adapter
    }

    private fun initObservers() {
        taskViewModel.apply {
            taskListLiveData.observe(viewLifecycleOwner) {
                adapter.setTaskList(it)
                updateUI(it.isNotEmpty())
            }
        }
    }

    private fun updateUI(isTaskAvailable: Boolean) = binding.apply {
        if (!isTaskAvailable) {
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
    }

    private fun deleteTaskFromFirestore(timeId: Long) {
        val query = tasksCollectionRef.whereEqualTo("timeCreated", timeId)
        query.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.documents.isNotEmpty()) {
                val documentToDelete = querySnapshot.documents[0]
                documentToDelete.reference.delete()
                    .addOnSuccessListener {
                        Log.d(HomeActivity.TAG, "Task deleted with timeCreated: $timeId")
                    }
                    .addOnFailureListener { exception ->
                        Log.w(HomeActivity.TAG, "Error deleting task", exception)
                    }
            } else {
                Log.w(HomeActivity.TAG, "Task with timeCreated $timeId not found")
            }
        }
            .addOnFailureListener { exception ->
                Log.w(HomeActivity.TAG, "Error querying tasks", exception)
            }
    }


    override fun getPageName() = PageName.IndexFragment

    companion object {
        @JvmStatic
        fun newInstance() = IndexFragment().apply {

        }

        const val INDEX = "Index"
    }
}