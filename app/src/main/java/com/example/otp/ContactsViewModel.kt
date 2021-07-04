package com.example.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otp.models.ContactsList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val TAG = "ContactsViewModel"

class ContactsViewModel : ViewModel() {


    private val _contactsList = MutableLiveData<ContactsList>()
    val contactsList: LiveData<ContactsList>
        get() = _contactsList


    fun getContactsFromJson(json: String) {
        val gson = Gson()

        try {
            val objectType = object : TypeToken<ContactsList>() {}.type
            val outputArray: ContactsList = gson.fromJson(json, objectType)

            _contactsList.value = outputArray
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}