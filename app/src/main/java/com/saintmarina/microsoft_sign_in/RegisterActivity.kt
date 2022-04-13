package com.saintmarina.microsoft_sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saintmarina.microsoft_sign_in.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Validation email
        binding.editTextEmail.setOnFocusChangeListener { _, hasFocus ->
             if (!hasFocus) {
                 binding.editTextEmailError.text = emailValidationErrorMessage()
             }
        }

        //Validating password
        binding.editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.editTextPasswordError.text = passwordValidationErrorMessage()
            }
        }

        //Validating website
        binding.editTextWebsite.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.editTextWebsiteError.text = websiteValidationErrorMessage()
            }

        }

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
        return  if (binding.editTextPassword.text.toString().isEmpty()) {
            "*This is required field. Enter a password"
        } else {
            ""
        }
    }

    private fun websiteValidationErrorMessage():String {
        val websitePattern = "[a-zA-Z0-9-]+\\.+[a-z]+".toRegex()
        return if (binding.editTextEmail.text.toString().isNotEmpty()) {
                    if (binding.editTextEmail.text.toString().trim().matches(websitePattern)) "" else "*Enter a valid url."
                } else {
                    ""
                }
    }

}