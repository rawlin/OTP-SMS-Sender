package com.example.otp.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.otp.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MessagesSentDatabaseTest {
    private lateinit var messagesSentDao: MessagesSentDao
    private lateinit var db: MessagesSentDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MessagesSentDatabase::class.java
        ).build()
        messagesSentDao = db.messagesSentDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadMessage() = runBlocking {
        val message = MessagesSent(
            id = 1,
            to = "+91123456789",
            name = "Rawlin",
            otp = "12345"
        )
        messagesSentDao.insert(message)

        val allMessageSentItems = messagesSentDao.getListOfMessagesSent().getOrAwaitValue()

        assertThat(allMessageSentItems[0], equalTo(message))
    }
}