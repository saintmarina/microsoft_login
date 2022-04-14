package com.saintmarina.microsoft_sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.saintmarina.microsoft_sign_in.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Validating email. Email must include @ and .
        binding.editTextEmail.setOnFocusChangeListener { _, hasFocus ->
             if (!hasFocus) {
                 binding.editTextEmailError.text = emailValidationErrorMessage()
             }
        }

        //Validating password. Password must be minimum 4 letters
        binding.editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.editTextPasswordError.text = passwordValidationErrorMessage()
            }
        }

        //Validating website is not necessary, because people might provide
        // instagram, github, linkedin, url etc.

        binding.cirRegisterButton.setOnClickListener {
            if (areAllFormFieldsValid()) {
                // Submit and start new activity
                val intent = Intent(this, ConfirmationPageActivity::class.java).apply {
                    putExtra("name", binding.editTextName.text.toString())
                    putExtra("email", binding.editTextEmail.text.toString())
                    putExtra("website", binding.editTextWebsite.text.toString())
                }
                startActivity(intent)
            } else {
                binding.editTextEmailError.text = emailValidationErrorMessage()
                binding.editTextPasswordError.text = passwordValidationErrorMessage()
            }
        }
    }

    private fun areAllFormFieldsValid():Boolean {
        return emailValidationErrorMessage().isEmpty() &&
                passwordValidationErrorMessage().isEmpty()
    }

    private fun emailValidationErrorMessage():String {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        return if (binding.editTextEmail.text.toString().isEmpty()) {
            "*This is required field. Enter your email address."
        } else {
            if (binding.editTextEmail.text.toString().trim().matches(emailPattern)) {
                ""
            } else {
                "*This is required field. Enter a valid email address."
            }
        }

    }

    private fun passwordValidationErrorMessage():String {
        val password = binding.editTextPassword.text.toString()
        return when {
            password.isEmpty() -> "*This is required field. Enter a password"
            password.length < 4 -> "*Password has to be minimum 4 characters"
            else -> ""
        }

    }

}