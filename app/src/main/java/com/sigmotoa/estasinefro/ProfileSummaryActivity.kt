package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ProfileSummaryActivity : BaseActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var languageTextView: TextView
    private lateinit var creationDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_summary)

        initializeViews()
        loadUserProfile()
        setupButtons()
    }

    private fun initializeViews() {
        nameTextView = findViewById(R.id.nameTextView)
        ageTextView = findViewById(R.id.ageTextView)
        languageTextView = findViewById(R.id.languageTextView)
        creationDateTextView = findViewById(R.id.creationDateTextView)
    }

    private fun loadUserProfile() {
        // Cargar información del perfil
        val userName = UserProfileActivity.getUserName(this)
        val userAge = UserProfileActivity.getUserAge(this)
        val selectedLanguage = LanguageSelectionActivity.getSavedLanguage(this)

        // Mostrar nombre
        nameTextView.text = userName

        // Mostrar edad con formato
        val ageText = if (userAge.isNotEmpty()) {
            "$userAge ${getString(R.string.years_old)}"
        } else {
            getString(R.string.age_info)
        }
        ageTextView.text = ageText

        // Mostrar idioma
        val languageName = getLanguageDisplayName(selectedLanguage)
        languageTextView.text = languageName

        // Mostrar fecha de creación
        val creationDate = getProfileCreationDate()
        val dateText = String.format(getString(R.string.profile_created_date), creationDate)
        creationDateTextView.text = dateText
    }

    private fun getLanguageDisplayName(languageCode: String): String {
        return when (languageCode) {
            "es" -> "Español"
            "en" -> "English"
            "fr" -> "Français"
            "de" -> "Deutsch"
            "it" -> "Italiano"
            "pt" -> "Português"
            else -> "Español"
        }
    }

    private fun getProfileCreationDate(): String {
        val prefs = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val timestamp = prefs.getLong("profile_creation_timestamp", System.currentTimeMillis())

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }

    private fun setupButtons() {
        // Botón Ver Créditos
        val creditsButton = findViewById<View>(R.id.creditsButton)
        creditsButton.setOnClickListener {
            navigateToCredits()
        }

        // Botón Continuar a la app
        val continueButton = findViewById<View>(R.id.continueButton)
        continueButton.setOnClickListener {
            navigateToMainApp()
        }

        // Botón Editar Perfil
        val editProfileButton = findViewById<View>(R.id.editProfileButton)
        editProfileButton.setOnClickListener {
            navigateToEditProfile()
        }
    }

    private fun navigateToCredits() {
        val intent = Intent(this, CreditsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainApp() {
        // Marcar que el resumen del perfil ha sido mostrado
        ProfileSummaryActivity.markProfileSummaryShown(this)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun navigateToEditProfile() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        fun isProfileSummaryShown(context: Context): Boolean {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            return prefs.getBoolean("profile_summary_shown", false)
        }

        fun markProfileSummaryShown(context: Context) {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            prefs.edit()
                .putBoolean("profile_summary_shown", true)
                .putLong("profile_creation_timestamp", System.currentTimeMillis())
                .apply()
        }

        fun resetProfileSummary(context: Context) {
            val prefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            prefs.edit()
                .putBoolean("profile_summary_shown", false)
                .apply()
        }
    }
}