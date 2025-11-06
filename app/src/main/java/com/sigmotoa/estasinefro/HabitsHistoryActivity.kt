package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class HabitsHistoryActivity : BaseActivity() {

    private lateinit var backButton: ImageView
    private lateinit var totalRecordsTextView: TextView
    private lateinit var avgWaterTextView: TextView
    private lateinit var avgWeightTextView: TextView
    private lateinit var recordsRecyclerView: RecyclerView
    private lateinit var noRecordsTextView: TextView
    private lateinit var filterAllButton: TextView
    private lateinit var filterWeekButton: TextView
    private lateinit var filterMonthButton: TextView
    private lateinit var exportButton: TextView

    private lateinit var recordsAdapter: HabitsRecordsAdapter
    private var allRecords: List<HabitRecord> = emptyList()
    private var filteredRecords: List<HabitRecord> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_history)

        initializeViews()
        setupRecyclerView()
        setupClickListeners()
        loadRecords()
        updateStatistics()
    }

    private fun initializeViews() {
        backButton = findViewById(R.id.backButton)
        totalRecordsTextView = findViewById(R.id.totalRecordsTextView)
        avgWaterTextView = findViewById(R.id.avgWaterTextView)
        avgWeightTextView = findViewById(R.id.avgWeightTextView)
        recordsRecyclerView = findViewById(R.id.recordsRecyclerView)
        noRecordsTextView = findViewById(R.id.noRecordsTextView)
        filterAllButton = findViewById(R.id.filterAllButton)
        filterWeekButton = findViewById(R.id.filterWeekButton)
        filterMonthButton = findViewById(R.id.filterMonthButton)
        exportButton = findViewById(R.id.exportButton)
    }

    private fun setupRecyclerView() {
        recordsAdapter = HabitsRecordsAdapter()
        recordsRecyclerView.layoutManager = LinearLayoutManager(this)
        recordsRecyclerView.adapter = recordsAdapter
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }

        filterAllButton.setOnClickListener {
            filterRecords("all")
        }

        filterWeekButton.setOnClickListener {
            filterRecords("week")
        }

        filterMonthButton.setOnClickListener {
            filterRecords("month")
        }

        exportButton.setOnClickListener {
            exportData()
        }
    }

    private fun loadRecords() {
        val prefs = getSharedPreferences("HabitsTracking", Context.MODE_PRIVATE)
        val recordsJson = prefs.getString("habit_records", "[]") ?: "[]"

        val gson = com.google.gson.Gson()
        val type = object : com.google.gson.reflect.TypeToken<List<HabitRecord>>() {}.type

        allRecords = try {
            gson.fromJson<List<HabitRecord>>(recordsJson, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }

        // Sort by timestamp (most recent first)
        allRecords = allRecords.sortedByDescending { it.timestamp }

        filteredRecords = allRecords
        updateRecordsDisplay()

        // Debug: Show loaded records count
        Toast.makeText(this, "📊 Registros cargados: ${allRecords.size}", Toast.LENGTH_SHORT).show()
    }

    private fun filterRecords(filter: String) {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis

        filteredRecords = when (filter) {
            "week" -> {
                calendar.add(Calendar.DAY_OF_YEAR, -7)
                allRecords.filter { it.timestamp >= calendar.timeInMillis }
            }
            "month" -> {
                calendar.add(Calendar.DAY_OF_YEAR, -30)
                allRecords.filter { it.timestamp >= calendar.timeInMillis }
            }
            else -> allRecords
        }

        updateRecordsDisplay()
        updateStatistics()

        Toast.makeText(this, "Mostrando ${filteredRecords.size} registros", Toast.LENGTH_SHORT).show()
    }

    private fun updateRecordsDisplay() {
        if (filteredRecords.isEmpty()) {
            recordsRecyclerView.visibility = View.GONE
            noRecordsTextView.visibility = View.VISIBLE
        } else {
            recordsRecyclerView.visibility = View.VISIBLE
            noRecordsTextView.visibility = View.GONE
            recordsAdapter.updateRecords(filteredRecords)
        }
    }

    private fun updateStatistics() {
        totalRecordsTextView.text = filteredRecords.size.toString()

        if (filteredRecords.isNotEmpty()) {
            val avgWater = filteredRecords.map { it.waterIntake }.average()
            avgWaterTextView.text = "${avgWater.toInt()}ml"

            val avgWeight = filteredRecords.map { it.weight }.average()
            avgWeightTextView.text = "${String.format("%.1f", avgWeight)}kg"
        } else {
            avgWaterTextView.text = "0ml"
            avgWeightTextView.text = "0kg"
        }
    }

    private fun exportData() {
        if (filteredRecords.isEmpty()) {
            Toast.makeText(this, "No hay datos para exportar", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val exportText = generateExportText()
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Historial de Hábitos Saludables")
                putExtra(Intent.EXTRA_TEXT, exportText)
            }
            startActivity(Intent.createChooser(shareIntent, "Compartir historial"))
        } catch (e: Exception) {
            Toast.makeText(this, "Error al exportar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateExportText(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val builder = StringBuilder()

        builder.appendLine("📊 HISTORIAL DE HÁBITOS SALUDABLES")
        builder.appendLine("Generado: ${dateFormat.format(Date())}")
        builder.appendLine("=".repeat(50))
        builder.appendLine()

        filteredRecords.forEachIndexed { index, record ->
            builder.appendLine("📋 REGISTRO #${index + 1}")
            builder.appendLine("📅 Fecha: ${record.date}")
            builder.appendLine("⏰ Hora: ${record.time}")
            builder.appendLine("💧 Agua: ${record.waterIntake}ml")
            builder.appendLine("🧂 Sal: ${record.saltIntake}g")

            builder.appendLine("⚖️ Peso: ${record.weight}kg")
            builder.appendLine("🚽 Frecuencia urinaria: ${record.urinationFrequency} veces")
            builder.appendLine("🦵 Hinchazón: ${record.swellingLevel}")
            builder.appendLine("-".repeat(30))
            builder.appendLine()
        }

        // Add summary statistics
        builder.appendLine("📈 RESUMEN ESTADÍSTICO")
        builder.appendLine("=".repeat(50))
        builder.appendLine("Total de registros: ${filteredRecords.size}")

        if (filteredRecords.isNotEmpty()) {
            builder.appendLine("Promedio de agua: ${filteredRecords.map { it.waterIntake }.average().toInt()}ml")
            builder.appendLine("Promedio de sal: ${String.format("%.1f", filteredRecords.map { it.saltIntake }.average())}g")
            builder.appendLine("Promedio de peso: ${String.format("%.1f", filteredRecords.map { it.weight }.average())}kg")

        }

        return builder.toString()
    }
}