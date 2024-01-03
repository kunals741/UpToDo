package com.kunal.uptodo.views

import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kunal.uptodo.shared_pref.UserSession

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val userSession = UserSession(this)
        val db = Firebase.firestore
    }
}