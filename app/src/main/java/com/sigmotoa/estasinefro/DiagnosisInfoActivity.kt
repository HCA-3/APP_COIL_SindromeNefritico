package com.sigmotoa.estasinefro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DiagnosisInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis_info)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = getString(R.string.diagnosis_title)
    }

    private fun setupClickListeners() {
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val proteinuriaInfoButton = findViewById<TextView>(R.id.proteinuriaInfoButton)
        proteinuriaInfoButton.setOnClickListener {
            ExplanationActivity.start(this, "proteinuria")
        }
    }
}