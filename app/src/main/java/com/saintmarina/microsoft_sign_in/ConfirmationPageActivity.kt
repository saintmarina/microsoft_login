package com.saintmarina.microsoft_sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ConfirmationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_page)
        val text = findViewById<TextView>(R.id.textConfirmationPage)
        val bundle = intent.extras!!
        text.text = "Hello, ${bundle.getString("name")}! Your email is: ${bundle.getString("email")},this is your website: ${bundle.getString("website")}"
    }
}