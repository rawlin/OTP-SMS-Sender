package com.example.otp.ui.message_sending_screen

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.otp.R
import com.example.otp.databinding.ActivityMessageSendingBinding
import com.example.otp.models.Contacts
import com.example.otp.ui.main_screen.MainActivity
import com.example.otp.utils.AppConstants.CONTACT_DETAILS
import com.example.otp.utils.Resource
import com.example.otp.utils.runAfterDelay
import com.example.otp.utils.showToast

class MessageSendingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageSendingBinding
    private val viewModel by viewModels<MessageSendingViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageSendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.extras?.get(CONTACT_DETAILS) as Contacts

        binding.apply {
            receiverDetails.text = getString(R.string.sending_otp_details, contact.phoneNumber)

            viewModel.otp.observe(this@MessageSendingActivity) {
                otpText.setText(getString(R.string.your_otp_is, it))
            }

            otpText.isEnabled = false

            sendButton.setOnClickListener {
                val text = otpText.text.toString()
                viewModel.sendOtpMessage(body = text, contact.phoneNumber, contact.name)
            }

        }

        viewModel.messageSentStatus.observe(this) { status ->
            when (status) {
                is Resource.Success -> {
                    binding.apply {
                        progressBar.isVisible = false
                    }
                    showToast("OTP sent successfully")
                    runAfterDelay({
                        startActivity(Intent(this, MainActivity::class.java).apply {
                            flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                        })
                        finish()
                    }, Toast.LENGTH_SHORT.toLong())
                }
                is Resource.Loading -> {
                    binding.apply {
                        progressBar.isVisible = true
                    }
                }
                is Resource.Failure -> {
                    binding.apply {
                        progressBar.isVisible = false
                    }
                    showToast(
                        status.exception
                            ?: "Some problem occurred while sending OTP. Please Try again"
                    )
                }
            }

        }
    }
}