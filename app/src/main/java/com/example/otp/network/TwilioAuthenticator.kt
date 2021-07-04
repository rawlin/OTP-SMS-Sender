package com.example.otp.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response


class TwilioAuthenticator(user: String, password: String) : Interceptor {

    private val credentials = Credentials.basic(user, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val authenticationRequest = request.newBuilder()
            .header("Authorization", credentials).build()
        return chain.proceed(authenticationRequest)
    }
}