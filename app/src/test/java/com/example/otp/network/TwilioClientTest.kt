package com.example.otp.network

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class TwilioClientTest {
    private val api: TwilioService = RetrofitInstance.api

    @Test
    fun `Send SMS using Twilio API`() = runBlocking {
        val response = api.sendMessage(to = "+919588660141", body = "Test SMS")
        assertNotNull(response)
    }
}