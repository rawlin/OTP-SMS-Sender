package com.example.otp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.otp.databinding.ActivityDetailsBinding
import com.example.otp.databinding.ActivityMainBinding
import com.example.otp.models.Contacts
import com.example.otp.utils.AppConstants.CONTACT_DETAILS

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.extras?.get(CONTACT_DETAILS) as Contacts

        binding.apply {
            username.text = getString(R.string.name, contact.name)
            phoneNumber.text = getString(R.string.phone_number, contact.phoneNumber)

            sendMessage.setOnClickListener {
                val intent = Intent(this@DetailsActivity, MessageSendingActivity::class.java).apply {
                    putExtra(CONTACT_DETAILS, contact)
                }
                startActivity(intent)
            }
        }
    }
}