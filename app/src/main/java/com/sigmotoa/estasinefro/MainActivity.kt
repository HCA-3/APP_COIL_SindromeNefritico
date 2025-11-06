package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Check if it's first launch or language not selected
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        val prefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isFirstLaunch = prefs.getBoolean("is_first_launch", true)

        if (isFirstLaunch) {
            // Mark as not first launch anymore
            prefs.edit().putBoolean("is_first_launch", false).apply()

            // Redirect to language selection
            val intent = Intent(this, LanguageSelectionActivity::class.java)
            startActivity(intent)
            finish()
        } else if (!UserProfileActivity.isProfileCompleted(this)) {
            // If language is selected but profile is not completed
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Update welcome message with user name
            updateWelcomeMessage()
            setupMedicalCards()
            setupAnimations()
        }
    }

    private fun updateWelcomeMessage() {
        val userName = UserProfileActivity.getUserName(this)
        val welcomeTextView = findViewById<android.widget.TextView>(R.id.textView)
        if (userName.isNotEmpty()) {
            welcomeTextView.text = String.format(getString(R.string.welcome_user), userName)
        }
    }

    private fun setupMedicalCards() {
        val nephroticCard = findViewById<androidx.cardview.widget.CardView>(R.id.nephroticCard)
        val postinfectiousCard = findViewById<androidx.cardview.widget.CardView>(R.id.postinfectiousCard)
        val habitsCard = findViewById<androidx.cardview.widget.CardView>(R.id.habitsCard)
        val surveyCard = findViewById<androidx.cardview.widget.CardView>(R.id.surveyCard)

        nephroticCard.setOnClickListener {
            val intent = Intent(this, MedicalInfoActivity::class.java)
            intent.putExtra("info_type", 0)
            applyTransitionAnimation()
            startActivity(intent)
        }

        postinfectiousCard.setOnClickListener {
            val intent = Intent(this, MedicalInfoActivity::class.java)
            intent.putExtra("info_type", 1)
            applyTransitionAnimation()
            startActivity(intent)
        }

        habitsCard.setOnClickListener {
            val intent = Intent(this, ImprovedHabitsTrackingActivity::class.java)
            applyTransitionAnimation()
            startActivity(intent)
        }

        surveyCard.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            applyTransitionAnimation()
            startActivity(intent)
        }

        // Configurar el clic del aviso médico
        val medicalDisclaimerCard = findViewById<CardView>(R.id.medicalDisclaimerCard)
        medicalDisclaimerCard.setOnClickListener {
            showMedicalDisclaimerDialog()
        }

        // Configurar botones de acción
        setupActionButtons()
    }

    private fun setupActionButtons() {
        val changeLanguageBtn = findViewById<Button>(R.id.changeLanguageBtn)
        val viewProfileBtn = findViewById<Button>(R.id.viewProfileBtn)

        changeLanguageBtn.setOnClickListener {
            val intent = Intent(this, LanguageSelectionActivity::class.java)
            applyTransitionAnimation()
            startActivity(intent)
        }

        viewProfileBtn.setOnClickListener {
            val intent = if (UserProfileActivity.isProfileCompleted(this)) {
                Intent(this, ProfileSummaryActivity::class.java)
            } else {
                Intent(this, UserProfileActivity::class.java)
            }
            applyTransitionAnimation()
            startActivity(intent)
        }
    }

    private fun setupAnimations() {
        // Animar el título
        val titleTextView = findViewById<android.widget.TextView>(R.id.textView)
        com.sigmotoa.estasinefro.AnimationUtils.slideDownAnimation(this, titleTextView)

        // Animar los botones de acción
        val changeLanguageBtn = findViewById<Button>(R.id.changeLanguageBtn)
        val viewProfileBtn = findViewById<Button>(R.id.viewProfileBtn)

        com.sigmotoa.estasinefro.AnimationUtils.fadeInAnimation(this, changeLanguageBtn)
        com.sigmotoa.estasinefro.AnimationUtils.fadeInAnimation(this, viewProfileBtn)

        // Configurar animaciones de presión para botones
        com.sigmotoa.estasinefro.AnimationUtils.setButtonPressAnimation(this, changeLanguageBtn)
        com.sigmotoa.estasinefro.AnimationUtils.setButtonPressAnimation(this, viewProfileBtn)

        // Configurar animaciones para las tarjetas médicas
        val nephroticCard = findViewById<CardView>(R.id.nephroticCard)
        val postinfectiousCard = findViewById<CardView>(R.id.postinfectiousCard)
        val habitsCard = findViewById<CardView>(R.id.habitsCard)
        val surveyCard = findViewById<CardView>(R.id.surveyCard)
        val medicalDisclaimerCard = findViewById<CardView>(R.id.medicalDisclaimerCard)

        // Animar entrada de las tarjetas
        val cards = listOf(nephroticCard, postinfectiousCard, habitsCard, surveyCard, medicalDisclaimerCard)
        val slideUpAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.slide_up)

        cards.forEachIndexed { index, card ->
            slideUpAnim.startOffset = (index * 100).toLong()
            card.startAnimation(slideUpAnim)
        }

        // Configurar animaciones de presión para tarjetas
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, nephroticCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, postinfectiousCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, habitsCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, surveyCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, medicalDisclaimerCard)
    }

    private fun applyTransitionAnimation() {
        overridePendingTransition(
            R.anim.transition_slide_in_right,
            R.anim.transition_slide_out_left
        )
    }

    fun changeLanguage(view: android.view.View) {
        // Resetear el resumen del perfil para que se muestre nuevamente después de cambiar idioma
        ProfileSummaryActivity.resetProfileSummary(this)
        val intent = Intent(this, LanguageSelectionActivity::class.java)
        startActivity(intent)
    }

    fun viewProfile(view: android.view.View) {
        val intent = Intent(this, ProfileSummaryActivity::class.java)
        startActivity(intent)
    }

    private fun showMedicalDisclaimerDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.medical_disclaimer))
            .setMessage(getString(R.string.disclaimer_text))
            .setPositiveButton(getString(R.string.i_understand)) { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}