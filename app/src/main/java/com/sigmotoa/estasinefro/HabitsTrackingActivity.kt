package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class HabitsTrackingActivity : BaseActivity() {

    private lateinit var waterEditText: TextInputEditText
    private lateinit var saltEditText: TextInputEditText
    private lateinit var systolicEditText: TextInputEditText
    private lateinit var diastolicEditText: TextInputEditText
    private lateinit var weightEditText: TextInputEditText
    private lateinit var urinationEditText: TextInputEditText
    private lateinit var swellingRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_tracking)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        waterEditText = findViewById(R.id.waterEditText)
        saltEditText = findViewById(R.id.saltEditText)
        systolicEditText = findViewById(R.id.systolicEditText)
        diastolicEditText = findViewById(R.id.diastolicEditText)
        weightEditText = findViewById(R.id.weightEditText)
        urinationEditText = findViewById(R.id.urinationEditText)
        swellingRadioGroup = findViewById(R.id.swellingRadioGroup)
    }

    private fun setupClickListeners() {
        val saveButton = findViewById<Button>(R.id.saveButton)
        val historyButton = findViewById<Button>(R.id.historyButton)
        val backButton = findViewById<Button>(R.id.backButton)

        saveButton.setOnClickListener {
            saveHabitRecord()
        }

        historyButton.setOnClickListener {
            // TODO: Implement history viewing
            Toast.makeText(this, "Historial próximamente", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun saveHabitRecord() {
        // Validate inputs
        if (!validateInputs()) {
            return
        }

        // Get values
        val waterIntake = waterEditText.text.toString()
        val saltIntake = saltEditText.text.toString()
        val systolic = systolicEditText.text.toString()
        val diastolic = diastolicEditText.text.toString()
        val weight = weightEditText.text.toString()
        val urinationFreq = urinationEditText.text.toString()
        val swellingLevel = getSwellingLevel()

        // Save to SharedPreferences (in a real app, this would go to a database)
        val prefs = getSharedPreferences("HabitsTracking", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val timestamp = System.currentTimeMillis()
        editor.putString("record_$timestamp", createRecordString(waterIntake, saltIntake, systolic, diastolic, weight, urinationFreq, swellingLevel))
        editor.apply()

        Toast.makeText(this, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()
        clearFields()
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (waterEditText.text.toString().isEmpty()) {
            waterEditText.error = "Requerido"
            isValid = false
        }

        if (saltEditText.text.toString().isEmpty()) {
            saltEditText.error = "Requerido"
            isValid = false
        }

        if (systolicEditText.text.toString().isEmpty()) {
            systolicEditText.error = "Requerido"
            isValid = false
        }

        if (diastolicEditText.text.toString().isEmpty()) {
            diastolicEditText.error = "Requerido"
            isValid = false
        }

        if (weightEditText.text.toString().isEmpty()) {
            weightEditText.error = "Requerido"
            isValid = false
        }

        if (urinationEditText.text.toString().isEmpty()) {
            urinationEditText.error = "Requerido"
            isValid = false
        }

        return isValid
    }

    private fun getSwellingLevel(): String {
        return when (swellingRadioGroup.checkedRadioButtonId) {
            R.id.noSwellingRadio -> "Ninguno"
            R.id.mildSwellingRadio -> "Leve"
            R.id.moderateSwellingRadio -> "Moderado"
            R.id.severeSwellingRadio -> "Severo"
            else -> "No especificado"
        }
    }

    private fun createRecordString(water: String, salt: String, systolic: String, diastolic: String, weight: String, urination: String, swelling: String): String {
        return "Agua: ${water}ml, Sal: ${salt}g, Presión: $systolic/$diastolic, Peso: ${weight}kg, Orina: ${urination}veces, Hinchazón: $swelling"
    }

    private fun clearFields() {
        waterEditText.text?.clear()
        saltEditText.text?.clear()
        systolicEditText.text?.clear()
        diastolicEditText.text?.clear()
        weightEditText.text?.clear()
        urinationEditText.text?.clear()
        swellingRadioGroup.clearCheck()
    }
}