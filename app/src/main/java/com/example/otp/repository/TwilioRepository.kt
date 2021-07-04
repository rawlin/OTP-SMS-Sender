package com.example.otp.repository

import com.example.otp.database.MessagesSent
import com.example.otp.database.MessagesSentDatabase
import com.example.otp.network.RetrofitInstance

class TwilioRepository(private val db: MessagesSentDatabase) {

    suspend fun sendMessage(to: String, body: String) =
        RetrofitInstance.api.sendMessage(to = to, body = body)

    suspend fun insert(messageSent: MessagesSent) = db.messagesSentDao().insert(messageSent)

    fun getAllMessagesSent() = db.messagesSentDao().getListOfMessagesSent()

}