package com.saintmarina.microsoft_sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saintmarina.microsoft_sign_in.databinding.ActivityRegisterBinding


// TODO make the name to be a required filter
// TODO add a * to the name, email, password hints
// TODO add title "Create Profile" to the RegisterActivity page
// TODO add opacity to the facebook and google" icons
// TODO on the confirmation page: Name: Anna\n email:anna@maniuk.nyc \nwebsite:github.com/saintmarina
// TODO the check_mark is too big. Make it smaller
// TODO confirmation page take out the "Hello, user!" line. Add it to the confirmation text.

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Validating name. The name must not be empty
        binding.editTextName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.editTextNameError.text = nameValidationErrorMessage()
            }
        }

        // Validating email. Email must include @ and .
        binding.editTextEmail.setOnFocusChangeListener { _, hasFocus ->
             if (!hasFocus) {
                 binding.editTextEmailError.text = emailValidationErrorMessage()
             }
        }

        // Validating password. Password must be minimum 4 letters
        binding.editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.editTextPasswordError.text = passwordValidationErrorMessage()
            }
        }

        // As tempting it might be to validate the website string as a URL, it might
        // not be good: many people might want to submit something like "instagram: @username"
        // instead of a traditional URL. We let them do it.

        binding.cirRegisterButton.setOnClickListener {
            if (areAllFormFieldsValid()) {
                // Submit and start new activity
                val intent = Intent(this, ConfirmationPageActivity::class.java).apply {
                    // .toString() because we need to convert SpannableString to String
                    putExtra("name", getName())
                    putExtra("email", getEmail())
                    putExtra("website", getPassword())
                }
                startActivity(intent)
            } else {
                binding.editTextNameError.text = nameValidationErrorMessage()
                binding.editTextEmailError.text = emailValidationErrorMessage()
                binding.editTextPasswordError.text = passwordValidationErrorMessage()

            }
        }
    }

    private fun areAllFormFieldsValid():Boolean {
        return nameValidationErrorMessage() == null &&
                emailValidationErrorMessage() == null &&
                passwordValidationErrorMessage() == null
    }

    private fun nameValidationErrorMessage():String? {
        val name = getName()
        return when {
            name.isEmpty() -> "Please enter your name"
            else -> null
        }
    }

    private fun emailValidationErrorMessage():String? {
        val emailPattern = ".+@.+\\..+".toRegex()
        val email = getEmail()
        return when {
            email.isEmpty() -> "Please enter an email address"
            email.matches(emailPattern) -> null
            else -> "Please enter a valid email address"
        }
    }

    private fun passwordValidationErrorMessage():String? {
        val password = getPassword()
        return when {
            password.isEmpty() -> "Please enter a password"
            password.length < 4 -> "Password has to be minimum 4 characters"
            else -> null
        }
    }

    private fun getName():String {
        // .toString() because we need to convert SpannableString to String
        return binding.editTextName.text!!.trim().toString()
    }

    private fun getEmail():String {
        return binding.editTextEmail.text!!.trim().toString()
    }

    private fun getPassword():String {
        return binding.editTextPassword.text!!.trim().toString()
    }
}