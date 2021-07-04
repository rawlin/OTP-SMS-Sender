package com.example.otp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.otp.database.MessagesSent
import com.example.otp.database.MessagesSentDatabase
import com.example.otp.repository.TwilioRepository
import com.example.otp.utils.Resource
import kotlinx.coroutines.launch
import org.json.JSONObject


private const val TAG = "MessageSendingViewModel"

class MessageSendingViewModel(private val app: Application) : AndroidViewModel(app) {

    private val db = MessagesSentDatabase.getDatabase(app.applicationContext)
    private val repository = TwilioRepository(db)
    private val _otp = MutableLiveData<String>()
    val otp: LiveData<String>
        get() = _otp
    private val _messageSentStatus = MutableLiveData<Resource<Unit>>()
    val messageSentStatus: LiveData<Resource<Unit>>
        get() = _messageSentStatus

    init {
        generateOtp()
    }

    private fun generateOtp() {
        val otp = (100000..999999).random()
        _otp.value = otp.toString()
    }

    fun sendOtpMessage(body: String, to: String, name: String) = viewModelScope.launch {
        _messageSentStatus.value = Resource.Loading
        try {
            val response = repository.sendMessage(to, body)
            if (response.isSuccessful) {
                val msg = MessagesSent(
                    to = to,
                    otp = otp.value ?: "",
                    name = name
                )
                repository.insert(msg)
                _messageSentStatus.value = Resource.Success(Unit)
            } else {
                val jObjError = JSONObject(response.errorBody()!!.charStream().readText())
                val message = jObjError.getString("message")
                _messageSentStatus.value = Resource.Failure(message)
                Log.d(TAG, "sendOtpFailureMessage: $message")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _messageSentStatus.value = Resource.Failure(null)
        }

    }


}