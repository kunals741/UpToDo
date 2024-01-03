package com.kunal.uptodo.utils

import android.content.Context
import android.widget.Toast

fun validateEmail(emailId: String): Boolean {
    val EMAIL_PATTERN = Regex("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    return EMAIL_PATTERN.matches(emailId)
}

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
