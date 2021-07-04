package com.example.otp.network.response


import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("account_sid")
    val accountSid: String,
    @SerializedName("api_version")
    val apiVersion: String,
    val body: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("date_sent")
    val dateSent: String,
    @SerializedName("date_updated")
    val dateUpdated: String,
    val direction: String,
    @SerializedName("error_code")
    val errorCode: Any,
    @SerializedName("error_message")
    val errorMessage: Any,
    val from: String,
    @SerializedName("messaging_service_sid")
    val messagingServiceSid: String,
    @SerializedName("num_media")
    val numMedia: String,
    @SerializedName("num_segments")
    val numSegments: String,
    val price: Any,
    @SerializedName("price_unit")
    val priceUnit: Any,
    val sid: String,
    val status: String,
    @SerializedName("subresource_uris")
    val subresourceUris: SubresourceUris,
    val to: String,
    val uri: String,
    val message: String?
)