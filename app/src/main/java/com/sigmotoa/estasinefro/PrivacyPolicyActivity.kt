package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PrivacyPolicyActivity : AppCompatActivity() {

    private lateinit var acceptCheckBox: CheckBox
    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verificar si el usuario ya aceptó la política de privacidad
        if (hasAcceptedPrivacyPolicy()) {
            // Si ya aceptó, ir directamente a MainActivity
            navigateToMainActivity()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_privacy_policy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeViews()
        setupListeners()
        setupBackPressHandler()
    }

    private fun initializeViews() {
        acceptCheckBox = findViewById(R.id.acceptCheckBox)
        continueButton = findViewById(R.id.continueButton)
    }

    private fun setupListeners() {
        // Habilitar/deshabilitar el botón continuar según el estado del checkbox
        acceptCheckBox.setOnCheckedChangeListener { _, isChecked ->
            continueButton.isEnabled = isChecked
        }

        // Configurar el botón continuar
        continueButton.setOnClickListener {
            if (acceptCheckBox.isChecked) {
                savePrivacyPolicyAcceptance()
                navigateToMainActivity()
            }
        }
    }

    /**
     * Verifica si el usuario ya aceptó la política de privacidad
     */
    private fun hasAcceptedPrivacyPolicy(): Boolean {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_PRIVACY_ACCEPTED, false)
    }

    /**
     * Guarda que el usuario aceptó la política de privacidad
     */
    private fun savePrivacyPolicyAcceptance() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean(KEY_PRIVACY_ACCEPTED, true)
            putLong(KEY_ACCEPTANCE_DATE, System.currentTimeMillis())
            apply()
        }
    }

    /**
     * Navega a la MainActivity
     */
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Configurar el manejador del botón atrás
     * Prevenir que el usuario regrese sin aceptar la política
     */
    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Cerrar la aplicación si el usuario presiona atrás sin aceptar
                finishAffinity()
            }
        })
    }

    companion object {
        private const val PREFS_NAME = "AppPrefs"
        private const val KEY_PRIVACY_ACCEPTED = "privacy_policy_accepted"
        private const val KEY_ACCEPTANCE_DATE = "privacy_acceptance_date"

        /**
         * Método estático para verificar si se aceptó la política desde otras actividades
         */
        fun hasAcceptedPrivacyPolicy(context: Context): Boolean {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getBoolean(KEY_PRIVACY_ACCEPTED, false)
        }

        /**
         * Método para resetear la aceptación (útil para testing o si se actualiza la política)
         */
        fun resetPrivacyPolicyAcceptance(context: Context) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().apply {
                putBoolean(KEY_PRIVACY_ACCEPTED, false)
                remove(KEY_ACCEPTANCE_DATE)
                apply()
            }
        }
    }
}
