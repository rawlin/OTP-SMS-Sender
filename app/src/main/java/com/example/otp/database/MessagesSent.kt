package com.example.otp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_sent")
data class MessagesSent(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val to: String,
    val name: String,
    val otp: String,
    val time: Long = System.currentTimeMillis()
)