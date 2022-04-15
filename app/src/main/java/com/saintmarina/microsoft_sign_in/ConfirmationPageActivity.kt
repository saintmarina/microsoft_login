package com.saintmarina.microsoft_sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saintmarina.microsoft_sign_in.databinding.ActivityConfirmationPageBinding

class ConfirmationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConfirmationPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val confirmationText = binding.confirmationText
        val nameText = binding.nameText
        val emailText = binding.emailText
        val websiteText = binding.wesiteText

        val bundle = intent.extras!!
        val name = bundle.getString("name")!!
        val email = bundle.getString("email")!!
        val website = bundle.getString("website")!!

        confirmationText.text = getString(R.string.confirmation_text, name)
        nameText.text = getString(R.string.name_string, name)
        emailText.text = getString(R.string.email_string, email)
        if (website.isNotEmpty()) {
            websiteText.text = getString(R.string.website_string, website)
        }
    }
}