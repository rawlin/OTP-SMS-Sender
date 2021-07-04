package com.example.otp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contacts(
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String
): Parcelable