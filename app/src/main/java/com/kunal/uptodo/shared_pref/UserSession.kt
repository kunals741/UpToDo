package com.kunal.uptodo.shared_pref

import android.content.Context
import com.kunal.uptodo.constants.AppConstants.IS_LOGGED_IN
import com.kunal.uptodo.constants.AppConstants.IS_ONBOARDING_SCREEN_SHOWN
import com.kunal.uptodo.constants.AppConstants.USER_PREFS
import com.kunal.uptodo.constants.AppConstants.USER_UID

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

    fun setUserId(uid: String) {
        sharedPreferences.edit().putString(USER_UID, uid).apply()
    }

    fun getUserId(): String {
        return sharedPreferences.getString(USER_UID, "no_uid") ?: "no_uid"
    }

    fun clearPref() {
        sharedPreferences.edit().clear().apply()
    }
}