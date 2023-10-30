package com.kunal.uptodo.views

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, OnboardingFragment.newInstance())
            .commit()
    }

    override fun handleBack() {
        // Handle the back press event here
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun pageType() = PageName.MainActivity
}