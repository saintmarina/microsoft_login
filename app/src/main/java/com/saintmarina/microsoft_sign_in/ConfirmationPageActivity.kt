package com.saintmarina.microsoft_sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ConfirmationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_page)

        val confirmationText = findViewById<TextView>(R.id.confirmation_text)

        val bundle = intent.extras!!
        val name = bundle.getString("name")!!
        val email = bundle.getString("email")!!
        val website = bundle.getString("website")!!

        confirmationText.text = getString(R.string.confirmation_text, name, email, website)
    }
}