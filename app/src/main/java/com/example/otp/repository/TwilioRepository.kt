package com.example.otp.repository

import com.example.otp.database.MessagesSent
import com.example.otp.database.MessagesSentDatabase
import com.example.otp.network.RetrofitInstance
import com.example.otp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class TwilioRepository(private val db: MessagesSentDatabase) {

    suspend fun sendMessage(to: String, body: String, name: String): Resource<Unit> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = RetrofitInstance.api.sendMessage(to = to, body = body)
                if (response.isSuccessful) {
                    Resource.Success(Unit)
                } else {
                    val jObjError = JSONObject(response.errorBody()?.charStream()?.readText())
                    val message = jObjError.getString("message")
                    Resource.Failure(message)
                }
            } catch (e: Throwable) {
                Resource.Failure(e.message)
            }
        }

    suspend fun insert(messageSent: MessagesSent)= withContext(Dispatchers.IO) {
        try {
            db.messagesSentDao().insert(messageSent)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun getAllMessagesSent() = db.messagesSentDao().getListOfMessagesSent()

}