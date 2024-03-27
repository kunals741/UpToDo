package com.kunal.uptodo.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kunal.uptodo.R
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.databinding.ActivityMainBinding
import com.kunal.uptodo.shared_pref.UserSession


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userSession = UserSession(this)
        if (!userSession.isOnBoardingScreenShown()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OnboardingFragment.newInstance())
                .commit()
        } else if (userSession.isLoggedIn()) {
            HomeActivity.startHomeActivity(PageName.MainApplication, this)
        } else {
            showWelcomeLoginFragment()
        }
    }

    override fun handleBack() {
        // Handle the back press event here
//        val fragmentManager = supportFragmentManager
//        if (fragmentManager.backStackEntryCount > 0) {
//            fragmentManager.popBackStack()
//        } else {
//            onBackPressedDispatcher.onBackPressed()
//        }
        finish()
    }

    private fun showWelcomeLoginFragment() =
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.container, WelcomeLoginFragment.getInstance())
            .commit()

    override fun pageType() = PageName.MainActivity

    companion object {
        @JvmStatic
        fun startMainActivity(
            source: String,
            context: Context
        ) {
            val intent = Intent(context, MainActivity::class.java)
            intent.apply {
                putExtra(IntentKeyConstants.SOURCE, source)
            }
            context.startActivity(intent)
        }
    }
}