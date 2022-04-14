package com.saintmarina.microsoft_sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saintmarina.microsoft_sign_in.databinding.ActivityRegisterBinding


// TODO add a cross to all the editTexts fields, so user could delete everything they typed
// TODO make the name to be a required filter
// TODO add a * to the name, email, password hints
// TODO add title "Create Profile" to the RegisterActivity page
// TODO add opacity to the facebook and google" icons
// TODO on the confirmation page: Name: Anna\n email:anna@maniuk.nyc \nwebsite:github.com/saintmarina
// TODO the check_mark is too big. Make it smaller
// TODO make the checkmark green
// TODO confirmation page take out the "Hello, user!" line. Add it to the confirmation text.
// TODO underline "Already have an account?"
// TODO instead of "use other methods" --> Sign in with
// TODO "Submit" -->"Register"

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
                    putExtra("name", binding.editTextName.text.trim())
                    putExtra("email", binding.editTextEmail.text.trim())
                    putExtra("website", binding.editTextWebsite.text.trim())
                }
                startActivity(intent)
            } else {
                binding.editTextEmailError.text = emailValidationErrorMessage()
                binding.editTextPasswordError.text = passwordValidationErrorMessage()
            }
        }
    }

    private fun areAllFormFieldsValid():Boolean {
        return emailValidationErrorMessage() == null &&
                passwordValidationErrorMessage() == null
    }

    private fun emailValidationErrorMessage():String? {
        val emailPattern = ".+@.+\\..+".toRegex()
        // TODO make a when
        // create a variable and trim it before doing any checks
        return if (binding.editTextEmail.text.isEmpty()) {
            "Please enter an email address"
        } else {
            if (binding.editTextEmail.text.trim().matches(emailPattern)) {
                null
            } else {
                "Please enter a valid email address"
            }
        }
    }

    private fun passwordValidationErrorMessage():String? {
        val password = binding.editTextPassword.text
        return when {
            password.isEmpty() -> "Please enter a password"
            password.length < 4 -> "Password has to be minimum 4 characters"
            else -> null
        }
    }
}