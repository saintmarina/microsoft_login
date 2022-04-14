package com.saintmarina.microsoft_sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class ConfirmationPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_page)
        val confirmationText = findViewById<TextView>(R.id.confirmation_text)
        val textGreeting = findViewById<TextView>(R.id.greeting_text)
        val bundle = intent.extras!!
        val name = formatName(bundle.getString("name")!!)

        textGreeting.text = getString(R.string.greeting_text, if (name.isEmpty()) "" else ", $name")
        confirmationText.text = getString(R.string.confirmation_text, bundle.getString("email"), name, bundle.getString("website"))
    }

    private fun formatName(name: String):String {
        if (name.isEmpty() || name.isBlank()) return name
        return name.trim().split(" ")[0].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}