package com.example.otp.network

import com.example.otp.BuildConfig
import com.example.otp.network.response.MessageResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TwilioService {
    @FormUrlEncoded
    @POST("/2010-04-01/Accounts/${BuildConfig.SID}/Messages.json")
    suspend fun sendMessage(
        @Field("To")
        to: String,
        @Field("From")
        from: String = "+17708095243",
        @Field("Body")
        body: String
    ): Response<MessageResponse>


}