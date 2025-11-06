package com.sigmotoa.estasinefro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MedicalInfoActivity : BaseActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var backButton: Button
    private lateinit var diagnosisButton: Button
    private lateinit var edemaInfoButton: TextView

    // TextViews para síntomas
    private lateinit var symptomsTitleTextView: TextView
    private lateinit var symptom1TextView: TextView
    private lateinit var symptom2TextView: TextView
    private lateinit var symptom3TextView: TextView
    private lateinit var symptom4TextView: TextView

    private var currentInfoType = 0 // 0: Nephrotic Syndrome, 1: Post-infectious GN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_info)

        initializeViews()
        setupClickListeners()

        // Get the info type from intent
        currentInfoType = intent.getIntExtra("info_type", 0)
        updateContent()
    }

    private fun initializeViews() {
        titleTextView = findViewById(R.id.titleTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        diagnosisButton = findViewById(R.id.diagnosisButton)
        backButton = findViewById(R.id.backButton)
        edemaInfoButton = findViewById(R.id.edemaInfoButton)

        // Inicializar TextViews de síntomas
        symptomsTitleTextView = findViewById(R.id.symptomsTitleTextView)
        symptom1TextView = findViewById(R.id.symptom1TextView)
        symptom2TextView = findViewById(R.id.symptom2TextView)
        symptom3TextView = findViewById(R.id.symptom3TextView)
        symptom4TextView = findViewById(R.id.symptom4TextView)
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }

        diagnosisButton.setOnClickListener {
            if (currentInfoType == 0) {
                // Navigate to post-infectious GN info
                val intent = Intent(this, MedicalInfoActivity::class.java)
                intent.putExtra("info_type", 1)
                startActivity(intent)
                finish()
            } else {
                // Go to habits tracking
                val intent = Intent(this, ImprovedHabitsTrackingActivity::class.java)
                startActivity(intent)
            }
        }

        edemaInfoButton.setOnClickListener {
            ExplanationActivity.start(this, "edema")
        }
    }

    private fun updateContent() {
        when (currentInfoType) {
            0 -> {
                // Nephrotic Syndrome
                titleTextView.text = getString(R.string.ns_title)
                descriptionTextView.text = getString(R.string.ns_description)
                diagnosisButton.text = "Siguiente"
                edemaInfoButton.visibility = View.VISIBLE  // Mostrar botón de edema

                // Mostrar síntomas de Síndrome Nefrítico
                symptomsTitleTextView.text = getString(R.string.ns_symptoms)
                symptom1TextView.text = getString(R.string.ns_symptom1)
                symptom2TextView.text = getString(R.string.ns_symptom2)
                symptom3TextView.text = getString(R.string.ns_symptom3)
                symptom4TextView.text = getString(R.string.ns_symptom4)
            }
            1 -> {
                // Post-infectious GN
                titleTextView.text = getString(R.string.pgn_title)
                descriptionTextView.text = getString(R.string.pgn_description)
                diagnosisButton.text = "Ir a Seguimiento"
                edemaInfoButton.visibility = View.GONE  // Ocultar botón de edema

                // Mostrar síntomas de Glomerulonefritis Postinfecciosa
                symptomsTitleTextView.text = getString(R.string.pgn_symptoms)
                symptom1TextView.text = getString(R.string.pgn_symptom1)
                symptom2TextView.text = getString(R.string.pgn_symptom2)
                symptom3TextView.text = getString(R.string.pgn_symptom3)
                symptom4TextView.text = getString(R.string.pgn_symptom4)
            }
        }
    }
}