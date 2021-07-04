package com.example.otp.network

import com.example.otp.BuildConfig
import com.example.otp.utils.AppConstants.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val gson = GsonBuilder().create()

        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(TwilioAuthenticator(BuildConfig.SID, BuildConfig.AUTH_TOKEN))
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(TwilioService::class.java)
        }
    }
}