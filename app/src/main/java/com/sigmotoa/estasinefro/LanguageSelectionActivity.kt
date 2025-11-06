package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import java.util.*

class LanguageSelectionActivity : BaseActivity() {

    private var selectedLanguage: String = "es" // Default: Spanish
    private lateinit var selectedCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)

        setupLanguageCards()
        setupContinueButton()

        // Set default selection to Spanish
        selectLanguage(findViewById<CardView>(R.id.spanishCard), "es")

        // Animate cards entrance
        animateCardsEntrance()
    }

    private fun setupLanguageCards() {
        val spanishCard = findViewById<CardView>(R.id.spanishCard)
        val englishCard = findViewById<CardView>(R.id.englishCard)

        spanishCard.setOnClickListener {
            selectLanguage(spanishCard, "es")
        }

        englishCard.setOnClickListener {
            selectLanguage(englishCard, "en")
        }
    }

    private fun selectLanguage(card: CardView, languageCode: String) {
        // Reset previous selection
        if (::selectedCard.isInitialized) {
            resetCardAppearance(selectedCard)
        }

        // Set new selection
        selectedCard = card
        selectedLanguage = languageCode
        highlightSelectedCard(card)

        // Apply selection animation
        animateSelection(card)
    }

    private fun resetCardAppearance(card: CardView) {
        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.crimson_blaze))
    }

    private fun highlightSelectedCard(card: CardView) {
        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.gochujang_red))
    }

    private fun animateSelection(card: CardView) {
        // Scale animation
        com.sigmotoa.estasinefro.AnimationUtils.createScaleAnimation(card, 1.0f, 1.1f, 150)

        // Bounce effect
        card.postDelayed({
            com.sigmotoa.estasinefro.AnimationUtils.createScaleAnimation(card, 1.1f, 1.0f, 150)
        }, 150)

        // Pulse effect for emphasis
        card.postDelayed({
            com.sigmotoa.estasinefro.AnimationUtils.pulseAnimation(card)
        }, 300)
    }

    private fun setupContinueButton() {
        val continueButton = findViewById<View>(R.id.continueButton)
        continueButton.setOnClickListener {
            applyLanguageAndContinue()
        }
    }

    private fun applyLanguageAndContinue() {
        // Save language preference
        saveLanguagePreference(selectedLanguage)

        // Apply language change
        setLocale(this, selectedLanguage)

        // Show confirmation message
        val languageName = when(selectedLanguage) {
            "es" -> "Español"
            "en" -> "English"
            else -> "Español"
        }

        Toast.makeText(this, "Idioma seleccionado: $languageName", Toast.LENGTH_SHORT).show()

        // Navigate to user profile activity
        val intent = Intent(this, UserProfileActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun saveLanguagePreference(languageCode: String) {
        val prefs = getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE)
        prefs.edit().putString("selected_language", languageCode).apply()
    }

    private fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    companion object {
        fun getSavedLanguage(context: Context): String {
            val prefs = context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE)
            return prefs.getString("selected_language", "es") ?: "es"
        }

        fun applySavedLanguage(context: Context) {
            val savedLanguage = getSavedLanguage(context)
            val locale = Locale(savedLanguage)
            Locale.setDefault(locale)

            val config = Configuration()
            config.setLocale(locale)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }

    private fun animateCardsEntrance() {
        val spanishCard = findViewById<CardView>(R.id.spanishCard)
        val englishCard = findViewById<CardView>(R.id.englishCard)

        // Animate Spanish card first
        com.sigmotoa.estasinefro.AnimationUtils.slideUpAnimation(this, spanishCard)

        // Animate English card with delay
        englishCard.postDelayed({
            com.sigmotoa.estasinefro.AnimationUtils.slideUpAnimation(this, englishCard)
        }, 200)

        // Animate continue button
        val continueButton = findViewById<View>(R.id.continueButton)
        continueButton.postDelayed({
            com.sigmotoa.estasinefro.AnimationUtils.fadeInAnimation(this, continueButton)
        }, 400)
    }
}