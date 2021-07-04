package com.example.otp.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.util.*

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}

inline fun <T> T.runAfterDelay(crossinline block: T.() -> Unit, delay: Long) {
    Timer().schedule(object : TimerTask() {
        override fun run() {
            block()
        }
    }, delay)
}

fun getDateAndTimeString(milliSeconds: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}
