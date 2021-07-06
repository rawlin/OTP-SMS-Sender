package com.example.otp.ui.sentMessageList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otp.database.MessagesSent
import com.example.otp.database.MessagesSentDatabase
import com.example.otp.repository.TwilioRepository

class MessagesSentViewModel(private val app: Application): AndroidViewModel(app) {

    private val database = MessagesSentDatabase.getDatabase(app.applicationContext)
    private val repository = TwilioRepository(database)
    val messagesSentList: LiveData<List<MessagesSent>>
        get() = repository.getAllMessagesSent()



}