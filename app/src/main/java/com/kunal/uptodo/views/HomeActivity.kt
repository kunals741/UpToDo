package com.kunal.uptodo.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.ActivityHomeBinding


class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(IndexFragment.newInstance(null), IndexFragment.INDEX)
        showAddTaskBottomsheet()
        setBottomNavigationListener()
    }

    private fun setBottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showFragment(IndexFragment.newInstance(null), IndexFragment.INDEX)
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

    private fun showAddTaskBottomsheet(){
        binding.ivPlusBtn.setOnClickListener {
            AddTaskBottomsheet.newInstance(pageType(), supportFragmentManager) {
                showFragment(IndexFragment.newInstance(it), IndexFragment.INDEX)
            }
        }
    }

    override fun handleBack() {
        //todo handle later
    }

    override fun pageType(): String = PageName.HomeActivity


    companion object {
        @JvmStatic
        fun startHomeActivity(
            source: String,
            activity: Activity
        ) {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.apply {
                putExtra(IntentKeyConstants.SOURCE, source)
            }
            activity.startActivity(intent)
        }
    }
}