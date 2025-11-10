package com.sigmotoa.estasinefro

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Clase base que maneja el idioma consistentemente en toda la aplicación
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply saved language before setting content view
        LanguageSelectionActivity.applySavedLanguage(this)
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context?) {
        // Apply language when context is attached
        val savedLanguage = LanguageSelectionActivity.getSavedLanguage(newBase ?: return)
        val locale = Locale(savedLanguage)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        super.attachBaseContext(newBase.createConfigurationContext(config))
    }

    /**
     * Reinicia la actividad actual para aplicar cambios de idioma
     */
    protected fun restartActivity() {
        val intent = intent
        finish()
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    /**
     * Sobrescribe finish() para aplicar animaciones de regreso
     */
    override fun finish() {
        super.finish()
        applyBackTransitionAnimation()
    }

    /**
     * Aplica animaciones de transición al regresar
     */
    private fun applyBackTransitionAnimation() {
        overridePendingTransition(
            R.anim.transition_slide_in_left,
            R.anim.transition_slide_out_right
        )
    }

    /**
     * Aplica animaciones de transición al avanzar
     */
    protected fun applyForwardTransitionAnimation() {
        overridePendingTransition(
            R.anim.transition_slide_in_right,
            R.anim.transition_slide_out_left
        )
    }
}