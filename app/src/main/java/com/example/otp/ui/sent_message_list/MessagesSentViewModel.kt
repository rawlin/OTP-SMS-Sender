package com.example.otp.ui.sent_message_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.otp.database.MessagesSent
import com.example.otp.database.MessagesSentDatabase
import com.example.otp.repository.TwilioRepository

class MessagesSentViewModel(private val app: Application): AndroidViewModel(app) {

    private val database = MessagesSentDatabase.getDatabase(app.applicationContext)
    private val repository = TwilioRepository(database)
    val messagesSentList: LiveData<List<MessagesSent>>
        get() = repository.getAllMessagesSent()

}