package com.example.otp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MessagesSent::class], version = 1)
abstract class MessagesSentDatabase : RoomDatabase() {

    abstract fun messagesSentDao(): MessagesSentDao

    companion object {
        @Volatile
        private var INSTANCE: MessagesSentDatabase? = null

        fun getDatabase(context: Context): MessagesSentDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessagesSentDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}