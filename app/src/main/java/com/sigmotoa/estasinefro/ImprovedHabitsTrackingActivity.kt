package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class ImprovedHabitsTrackingActivity : BaseActivity() {

    private lateinit var currentDateTextView: TextView
    private lateinit var waterSpinner: Spinner
    private lateinit var saltSpinner: Spinner
    private lateinit var weightSpinner: Spinner
    private lateinit var urinationSpinner: Spinner
    private lateinit var swellingRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_tracking_improved)

        initializeViews()
        setupSpinners()
        setupClickListeners()
        updateCurrentDate()
    }

    private fun initializeViews() {
        currentDateTextView = findViewById(R.id.currentDateTextView)
        waterSpinner = findViewById(R.id.waterSpinner)
        saltSpinner = findViewById(R.id.saltSpinner)
        weightSpinner = findViewById(R.id.weightSpinner)
        urinationSpinner = findViewById(R.id.urinationSpinner)
        swellingRadioGroup = findViewById(R.id.swellingRadioGroup)
    }

    private fun setupSpinners() {
        // Water intake options (in ml)
        val waterOptions = arrayOf(
            "Menos de 1000ml",
            "1000-1500ml",
            "1500-2000ml",
            "2000-2500ml",
            "2500-3000ml",
            "Más de 3000ml"
        )
        waterSpinner.adapter = android.widget.ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            waterOptions
        )

        // Salt intake options (in grams)
        val saltOptions = arrayOf(
            "Menos de 3g",
            "3-5g",
            "5-7g",
            "7-10g",
            "Más de 10g"
        )
        saltSpinner.adapter = android.widget.ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            saltOptions
        )

        // Weight options (in kg)
        val weightOptions = arrayOf(
            "Menos de 50kg",
            "50-60kg",
            "60-70kg",
            "70-80kg",
            "80-90kg",
            "90-100kg",
            "Más de 100kg"
        )
        weightSpinner.adapter = android.widget.ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            weightOptions
        )

        // Urination frequency options
        val urinationOptions = arrayOf(
            "Menos de 3 veces",
            "3-4 veces",
            "4-6 veces",
            "6-8 veces",
            "8-10 veces",
            "Más de 10 veces"
        )
        urinationSpinner.adapter = android.widget.ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            urinationOptions
        )
    }

    private fun setupClickListeners() {
        val backButton = findViewById<ImageView>(R.id.backButton)
        val saveButton = findViewById<TextView>(R.id.saveButton)
        val historyButton = findViewById<TextView>(R.id.historyButton)

        backButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            saveHabitRecord()
        }

        historyButton.setOnClickListener {
            openHistoryActivity()
        }
    }

    private fun updateCurrentDate() {
        val dateFormat = SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "ES"))
        val currentDate = dateFormat.format(Date())
        currentDateTextView.text = "📅 $currentDate"
    }

    private fun saveHabitRecord() {
        if (!validateInputs()) {
            return
        }

        // Get values from spinners
        val waterIntake = getWaterIntakeFromSpinner()
        val saltIntake = getSaltIntakeFromSpinner()
        val weight = getWeightFromSpinner()
        val urinationFreq = getUrinationFrequencyFromSpinner()
        val swellingLevel = getSwellingLevel()

        // Create habit record
        val habitRecord = HabitRecord(
            timestamp = System.currentTimeMillis(),
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
            time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
            waterIntake = waterIntake,
            saltIntake = saltIntake,
            weight = weight,
            urinationFrequency = urinationFreq,
            swellingLevel = swellingLevel
        )

        // Save to SharedPreferences
        saveHabitRecordToPrefs(habitRecord)

        // Show success message
        Toast.makeText(this, "✅ Registro guardado exitosamente", Toast.LENGTH_SHORT).show()

        // Debug: Show total records count
        val prefs = getSharedPreferences("HabitsTracking", Context.MODE_PRIVATE)
        val recordsJson = prefs.getString("habit_records", "[]") ?: "[]"
        val gson = com.google.gson.Gson()
        val type = object : com.google.gson.reflect.TypeToken<List<HabitRecord>>() {}.type
        val allRecords = try {
            gson.fromJson<List<HabitRecord>>(recordsJson, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
        Toast.makeText(this, "📊 Total de registros guardados: ${allRecords.size}", Toast.LENGTH_LONG).show()

        // Clear fields for next entry
        clearFields()

        // Show health tips based on the data
        showHealthTips(habitRecord)
    }

    private fun validateInputs(): Boolean {
        if (swellingRadioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor selecciona el nivel de hinchazón", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun getWaterIntakeFromSpinner(): Int {
        return when (waterSpinner.selectedItemPosition) {
            0 -> 500   // Menos de 1000ml
            1 -> 1250  // 1000-1500ml
            2 -> 1750  // 1500-2000ml
            3 -> 2250  // 2000-2500ml
            4 -> 2750  // 2500-3000ml
            5 -> 3250  // Más de 3000ml
            else -> 0
        }
    }

    private fun getSaltIntakeFromSpinner(): Double {
        return when (saltSpinner.selectedItemPosition) {
            0 -> 2.0   // Menos de 3g
            1 -> 4.0   // 3-5g
            2 -> 6.0   // 5-7g
            3 -> 8.5   // 7-10g
            4 -> 12.0  // Más de 10g
            else -> 0.0
        }
    }

    private fun getWeightFromSpinner(): Double {
        return when (weightSpinner.selectedItemPosition) {
            0 -> 45.0  // Menos de 50kg
            1 -> 55.0  // 50-60kg
            2 -> 65.0  // 60-70kg
            3 -> 75.0  // 70-80kg
            4 -> 85.0  // 80-90kg
            5 -> 95.0  // 90-100kg
            6 -> 105.0 // Más de 100kg
            else -> 0.0
        }
    }

    private fun getUrinationFrequencyFromSpinner(): Int {
        return when (urinationSpinner.selectedItemPosition) {
            0 -> 2     // Menos de 3 veces
            1 -> 3     // 3-4 veces
            2 -> 5     // 4-6 veces
            3 -> 7     // 6-8 veces
            4 -> 9     // 8-10 veces
            5 -> 11    // Más de 10 veces
            else -> 0
        }
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

    private fun saveHabitRecordToPrefs(record: HabitRecord) {
        val prefs = getSharedPreferences("HabitsTracking", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        // Get existing records
        val existingRecordsJson = prefs.getString("habit_records", "[]") ?: "[]"
        val gson = com.google.gson.Gson()
        val type = object : com.google.gson.reflect.TypeToken<MutableList<HabitRecord>>() {}.type

        // Load existing records or create new list
        val records = try {
            gson.fromJson<MutableList<HabitRecord>>(existingRecordsJson, type) ?: mutableListOf()
        } catch (e: Exception) {
            mutableListOf()
        }

        // Add new record
        records.add(record)

        // Save updated list as JSON
        val updatedRecordsJson = gson.toJson(records)
        editor.putString("habit_records", updatedRecordsJson)
        editor.apply()
    }

    private fun clearFields() {
        waterSpinner.setSelection(0)
        saltSpinner.setSelection(0)
        weightSpinner.setSelection(0)
        urinationSpinner.setSelection(0)
        swellingRadioGroup.clearCheck()
    }

    private fun showHealthTips(record: HabitRecord) {
        val tips = mutableListOf<String>()

        // Water intake tips
        if (record.waterIntake < 2000) {
            tips.add("💧 Considera aumentar tu consumo de agua al menos a 2000ml diarios")
        }

        // Salt intake tips
        if (record.saltIntake > 5.0) {
            tips.add("🧂 Tu consumo de sal es elevado. Intenta reducirlo a menos de 5g diarios")
        }

        // Swelling tips
        if (record.swellingLevel in listOf("Moderado", "Severo")) {
            tips.add("🦵 El nivel de hinchazón requiere atención médica")
        }

        // Weight monitoring tip
        tips.add("⚖️ Continúa monitoreando tu peso diariamente")

        if (tips.isNotEmpty()) {
            val message = tips.joinToString("\n\n")
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun openHistoryActivity() {
        val intent = Intent(this, HabitsHistoryActivity::class.java)
        startActivity(intent)
    }
}

