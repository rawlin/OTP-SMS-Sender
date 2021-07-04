package com.example.otp.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import kotlin.reflect.KClass

object JsonUtils {
    fun <T : Any> jsonToObject(type: KClass<T>,jsonText: String): T? {
        val gson = Gson()
        return try {
            val objectType = object : TypeToken<T>() {}.type
            gson.fromJson(jsonText, objectType) as T
        } catch (e : Exception) {
            e.printStackTrace()
            null
        }

    }
}