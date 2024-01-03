package com.kunal.uptodo.shared_pref

import android.content.Context
import com.kunal.uptodo.constants.AppConstants.IS_LOGGED_IN
import com.kunal.uptodo.constants.AppConstants.IS_ONBOARDING_SCREEN_SHOWN
import com.kunal.uptodo.constants.AppConstants.USER_PREFS

class UserSession(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun setIsOnBoardingScreenShown(shown: Boolean) {
        sharedPreferences.edit().putBoolean(IS_ONBOARDING_SCREEN_SHOWN, shown).apply()
    }

    fun isOnBoardingScreenShown(): Boolean {
        return sharedPreferences.getBoolean(IS_ONBOARDING_SCREEN_SHOWN, false)
    }
}