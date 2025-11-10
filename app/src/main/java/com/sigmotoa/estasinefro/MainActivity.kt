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

        // Animar los botones de acción con efecto de rebote
        val changeLanguageBtn = findViewById<Button>(R.id.changeLanguageBtn)
        val viewProfileBtn = findViewById<Button>(R.id.viewProfileBtn)

        // Animación de entrada con rebote para los botones
        val scaleInAnim = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        changeLanguageBtn.postDelayed({
            changeLanguageBtn.startAnimation(scaleInAnim)
        }, 300)

        viewProfileBtn.postDelayed({
            viewProfileBtn.startAnimation(scaleInAnim)
        }, 400)

        // Configurar animaciones de presión para botones
        com.sigmotoa.estasinefro.AnimationUtils.setButtonPressAnimation(this, changeLanguageBtn)
        com.sigmotoa.estasinefro.AnimationUtils.setButtonPressAnimation(this, viewProfileBtn)

        // Agregar efecto de pulso sutil a los botones
        changeLanguageBtn.postDelayed({
            startButtonPulseAnimation(changeLanguageBtn)
        }, 800)

        viewProfileBtn.postDelayed({
            startButtonPulseAnimation(viewProfileBtn)
        }, 900)

        // Configurar animaciones para las tarjetas médicas
        val nephroticCard = findViewById<CardView>(R.id.nephroticCard)
        val postinfectiousCard = findViewById<CardView>(R.id.postinfectiousCard)
        val habitsCard = findViewById<CardView>(R.id.habitsCard)
        val surveyCard = findViewById<CardView>(R.id.surveyCard)
        val medicalDisclaimerCard = findViewById<CardView>(R.id.medicalDisclaimerCard)

        // Animación de entrada con rebote para las tarjetas
        val scaleInAnim1 = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        val scaleInAnim2 = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        val scaleInAnim3 = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        val scaleInAnim4 = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        val scaleInAnim5 = AnimationUtils.loadAnimation(this, R.anim.scale_in)

        nephroticCard.postDelayed({
            nephroticCard.startAnimation(scaleInAnim1)
        }, 500)

        postinfectiousCard.postDelayed({
            postinfectiousCard.startAnimation(scaleInAnim2)
        }, 600)

        habitsCard.postDelayed({
            habitsCard.startAnimation(scaleInAnim3)
        }, 700)

        surveyCard.postDelayed({
            surveyCard.startAnimation(scaleInAnim4)
        }, 800)

        medicalDisclaimerCard.postDelayed({
            medicalDisclaimerCard.startAnimation(scaleInAnim5)
        }, 900)

        // Configurar animaciones de presión para tarjetas
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, nephroticCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, postinfectiousCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, habitsCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, surveyCard)
        com.sigmotoa.estasinefro.AnimationUtils.setCardPressAnimation(this, medicalDisclaimerCard)

        // Agregar efecto de pulso sutil a las tarjetas
        nephroticCard.postDelayed({
            startCardPulseAnimation(nephroticCard)
        }, 1000)

        postinfectiousCard.postDelayed({
            startCardPulseAnimation(postinfectiousCard)
        }, 1100)

        habitsCard.postDelayed({
            startCardPulseAnimation(habitsCard)
        }, 1200)

        surveyCard.postDelayed({
            startCardPulseAnimation(surveyCard)
        }, 1300)

        medicalDisclaimerCard.postDelayed({
            startCardPulseAnimation(medicalDisclaimerCard)
        }, 1400)
    }

    private fun applyTransitionAnimation() {
        overridePendingTransition(
            R.anim.transition_slide_in_right,
            R.anim.transition_slide_out_left
        )
    }

    private fun startButtonPulseAnimation(button: Button) {
        val scaleUp = android.animation.ObjectAnimator.ofFloat(button, "scaleX", 1.0f, 1.05f)
        val scaleUpY = android.animation.ObjectAnimator.ofFloat(button, "scaleY", 1.0f, 1.05f)
        val scaleDown = android.animation.ObjectAnimator.ofFloat(button, "scaleX", 1.05f, 1.0f)
        val scaleDownY = android.animation.ObjectAnimator.ofFloat(button, "scaleY", 1.05f, 1.0f)

        scaleUp.duration = 1000
        scaleUpY.duration = 1000
        scaleDown.duration = 1000
        scaleDownY.duration = 1000

        scaleUp.repeatCount = android.animation.ObjectAnimator.INFINITE
        scaleUpY.repeatCount = android.animation.ObjectAnimator.INFINITE
        scaleUp.repeatMode = android.animation.ObjectAnimator.REVERSE
        scaleUpY.repeatMode = android.animation.ObjectAnimator.REVERSE

        scaleUp.start()
        scaleUpY.start()
    }

    private fun startCardPulseAnimation(card: CardView) {
        val scaleX = android.animation.ObjectAnimator.ofFloat(card, "scaleX", 1.0f, 1.03f)
        val scaleY = android.animation.ObjectAnimator.ofFloat(card, "scaleY", 1.0f, 1.03f)

        scaleX.duration = 1500
        scaleY.duration = 1500

        scaleX.repeatCount = android.animation.ObjectAnimator.INFINITE
        scaleY.repeatCount = android.animation.ObjectAnimator.INFINITE
        scaleX.repeatMode = android.animation.ObjectAnimator.REVERSE
        scaleY.repeatMode = android.animation.ObjectAnimator.REVERSE

        scaleX.start()
        scaleY.start()
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