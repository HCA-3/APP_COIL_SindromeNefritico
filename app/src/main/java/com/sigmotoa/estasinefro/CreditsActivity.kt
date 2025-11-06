package com.sigmotoa.estasinefro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CreditsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val versionTextView = findViewById<TextView>(R.id.versionTextView)

        // Configurar textos
        titleTextView.text = getString(R.string.credits_title)
        versionTextView.text = getString(R.string.app_version)

        // Configurar información de equipos (puedes personalizar estos datos)
        setupDevelopmentTeam()
        setupMedicalTeam()
        setupTranslationTeam()
    }

    private fun setupDevelopmentTeam() {
        // Equipo de Desarrollo - 2 personas
        try {
            findViewById<android.widget.TextView>(R.id.dev1NameTextView)?.text = "David Castelblanco"
            findViewById<android.widget.TextView>(R.id.dev1EmailTextView)?.text = "dscastelblanco82@ucatolica.edu.co"
            // Usar imagen real del desarrollador si existe
            try {
                findViewById<android.widget.ImageView>(R.id.dev1ImageView)?.setImageResource(R.drawable.david_castelblanco)
            } catch (e: Exception) {
                // Si la imagen no existe, usar imagen por defecto
                findViewById<android.widget.ImageView>(R.id.dev1ImageView)?.setImageResource(R.drawable.ic_launcher_foreground)
            }

            findViewById<android.widget.TextView>(R.id.dev2NameTextView)?.text = "Andres Basto"
            findViewById<android.widget.TextView>(R.id.dev2EmailTextView)?.text = "afbasto49@ucatolica.edu.co"
            // Usar imagen real del desarrollador si existe
            try {
                findViewById<android.widget.ImageView>(R.id.dev2ImageView)?.setImageResource(R.drawable.andres_basto)
            } catch (e: Exception) {
                // Si la imagen no existe, usar imagen por defecto
                findViewById<android.widget.ImageView>(R.id.dev2ImageView)?.setImageResource(R.drawable.ic_launcher_foreground)
            }
        } catch (e: Exception) {
            // Manejar errores si las vistas no existen
            e.printStackTrace()
        }
    }

    private fun setupMedicalTeam() {
        // Equipo Médico - 5 personas
        val medicalNames = arrayOf(
            "Dr. Especialista Renal",
            "Dra. Nefróloga Clínica",
            "Dr. Investigador Médico",
            "Dra. Pediatra Especializada",
            "Dr. Médico General"
        )

        val medicalEmails = arrayOf(
            "renal@estasinefro.com",
            "nefro@estasinefro.com",
            "research@estasinefro.com",
            "pedia@estasinefro.com",
            "general@estasinefro.com"
        )

        // Usar imágenes específicas para el equipo médico
        val medicalPhotos = arrayOf(
            R.drawable.med1_photo,  // Especialista Renal
            R.drawable.med2_photo,  // Nefróloga Clínica
            R.drawable.med3_photo,  // Investigador Médico
            R.drawable.med4_photo,  // Pediatra Especializada
            R.drawable.med5_photo   // Médico General
        )

        try {
            for (i in medicalNames.indices) {
                if (i >= medicalPhotos.size) break

                val nameViewId = resources.getIdentifier("med${i+1}NameTextView", "id", packageName)
                val emailViewId = resources.getIdentifier("med${i+1}EmailTextView", "id", packageName)
                val photoViewId = resources.getIdentifier("med${i+1}ImageView", "id", packageName)

                if (nameViewId != 0) {
                    findViewById<android.widget.TextView>(nameViewId)?.text = medicalNames[i]
                }
                if (emailViewId != 0) {
                    findViewById<android.widget.TextView>(emailViewId)?.text = medicalEmails[i]
                }

                // Usar imagen específica del equipo médico
                if (photoViewId != 0 && i < medicalPhotos.size) {
                    try {
                        findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(medicalPhotos[i])
                    } catch (e: Exception) {
                        // Si la imagen no existe, usar imagen por defecto
                        findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(R.drawable.ic_launcher_foreground)
                    }
                }
            }
        } catch (e: Exception) {
            // Manejar errores si las vistas no existen
            e.printStackTrace()
        }
    }

    private fun setupTranslationTeam() {
        // Equipo de Traducción - 5 personas
        val translationNames = arrayOf(
            "Brandon Grassie",
            "Caitlin Neumann",
            "Dr. Carlos Rodríguez",
            "Dra. María González",
            "Lic. Sofía Martínez"
        )

        val translationEmails = arrayOf(
            "brandon.grassie@estasinefro.com",
            "caitlin.neumann@estasinefro.com",
            "carlos.rodriguez@estasinefro.com",
            "maria.gonzalez@estasinefro.com",
            "sofia.martinez@estasinefro.com"
        )

        // IDs de las imágenes para el equipo de traducción
        val translationPhotos = arrayOf(
            R.drawable.translator1_photo,  // Brandon Grassie
            R.drawable.translator2_photo,  // Caitlin Neumann
            R.drawable.translator3_photo,  // pendientes
            R.drawable.translator4_photo,  // pendientes
            R.drawable.translator5_photo   // pendientes
        )

        try {
            for (i in translationNames.indices) {
                if (i >= translationPhotos.size) break

                val nameViewId = resources.getIdentifier("trans${i+1}NameTextView", "id", packageName)
                val emailViewId = resources.getIdentifier("trans${i+1}EmailTextView", "id", packageName)
                val photoViewId = resources.getIdentifier("trans${i+1}ImageView", "id", packageName)

                if (nameViewId != 0) {
                    findViewById<android.widget.TextView>(nameViewId)?.text = translationNames[i]
                }
                if (emailViewId != 0) {
                    findViewById<android.widget.TextView>(emailViewId)?.text = translationEmails[i]
                }

                // Usar imagen específica del equipo de traducción
                if (photoViewId != 0 && i < translationPhotos.size) {
                    try {
                        findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(translationPhotos[i])
                    } catch (e: Exception) {
                        // Si la imagen no existe, usar imagen por defecto
                        findViewById<android.widget.ImageView>(photoViewId)?.setImageResource(R.drawable.ic_launcher_foreground)
                    }
                }
            }
        } catch (e: Exception) {
            // Manejar errores si las vistas no existen
            e.printStackTrace()
        }
    }

    private fun setupClickListeners() {
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}