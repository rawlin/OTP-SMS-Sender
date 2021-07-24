package com.example.otp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessagesSentDao {

    @Insert
    suspend fun insert(messagesSent: MessagesSent): Long

    @Query("SELECT * FROM messages_sent ORDER BY time DESC")
    fun getListOfMessagesSent(): LiveData<List<MessagesSent>>

}