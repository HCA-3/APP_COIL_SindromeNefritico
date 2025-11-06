package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UserProfileActivity : BaseActivity() {

    private lateinit var nameEditText: TextInputEditText
    private lateinit var ageEditText: TextInputEditText
    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var ageInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        initializeViews()
        setupSaveButton()
    }

    private fun initializeViews() {
        nameEditText = findViewById(R.id.nameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        nameInputLayout = findViewById(R.id.nameInputLayout)
        ageInputLayout = findViewById(R.id.ageInputLayout)
    }

    private fun setupSaveButton() {
        val saveButton = findViewById<View>(R.id.saveButton)
        saveButton.setOnClickListener {
            if (validateForm()) {
                saveUserProfile()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Validar nombre
        val name = nameEditText.text.toString().trim()
        if (name.isEmpty()) {
            nameInputLayout.error = getString(R.string.name_required)
            isValid = false
        } else if (name.length < 2) {
            nameInputLayout.error = "El nombre debe tener al menos 2 caracteres"
            isValid = false
        } else {
            nameInputLayout.error = null
        }

        // Validar edad
        val ageString = ageEditText.text.toString().trim()
        if (ageString.isEmpty()) {
            ageInputLayout.error = getString(R.string.age_required)
            isValid = false
        } else {
            try {
                val age = ageString.toInt()
                if (age < 1) {
                    ageInputLayout.error = getString(R.string.min_age)
                    isValid = false
                } else if (age > 120) {
                    ageInputLayout.error = getString(R.string.max_age)
                    isValid = false
                } else {
                    ageInputLayout.error = null
                }
            } catch (e: NumberFormatException) {
                ageInputLayout.error = getString(R.string.invalid_age)
                isValid = false
            }
        }

        return isValid
    }

    private fun saveUserProfile() {
        val name = nameEditText.text.toString().trim()
        val age = ageEditText.text.toString().trim()

        // Guardar en SharedPreferences
        val prefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("user_name", name)
            .putString("user_age", age)
            .putBoolean("profile_completed", true)
            .putLong("profile_creation_timestamp", System.currentTimeMillis())
            .apply()

        // Mostrar mensaje de éxito
        Toast.makeText(this, "¡Perfil guardado correctamente!", Toast.LENGTH_SHORT).show()

        // Navegar a ProfileSummaryActivity
        val intent = Intent(this, ProfileSummaryActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        fun isProfileCompleted(context: Context): Boolean {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            return prefs.getBoolean("profile_completed", false)
        }

        fun getUserName(context: Context): String {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            return prefs.getString("user_name", "") ?: ""
        }

        fun getUserAge(context: Context): String {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            return prefs.getString("user_age", "") ?: ""
        }

        fun clearUserProfile(context: Context) {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            prefs.edit().clear().apply()
        }
    }
}