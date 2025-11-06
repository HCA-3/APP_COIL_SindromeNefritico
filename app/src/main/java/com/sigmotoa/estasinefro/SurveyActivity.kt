package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SurveyActivity : BaseActivity() {

    private lateinit var question1RadioGroup: RadioGroup
    private lateinit var question2RadioGroup: RadioGroup
    private lateinit var question3RadioGroup: RadioGroup
    private lateinit var question4RadioGroup: RadioGroup
    private lateinit var question5RadioGroup: RadioGroup
    private lateinit var question6RadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        question1RadioGroup = findViewById(R.id.question1RadioGroup)
        question2RadioGroup = findViewById(R.id.question2RadioGroup)
        question3RadioGroup = findViewById(R.id.question3RadioGroup)
        question4RadioGroup = findViewById(R.id.question4RadioGroup)
        question5RadioGroup = findViewById(R.id.question5RadioGroup)
        question6RadioGroup = findViewById(R.id.question6RadioGroup)
    }

    private fun setupClickListeners() {
        val evaluateButton = findViewById<Button>(R.id.evaluateButton)
        val backButton = findViewById<Button>(R.id.backButton)

        evaluateButton.setOnClickListener {
            evaluateSurvey()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun evaluateSurvey() {
        if (!validateAllQuestions()) {
            Toast.makeText(this, "Por favor responda todas las preguntas", Toast.LENGTH_SHORT).show()
            return
        }

        val score = calculateRiskScore()
        showResults(score)
    }

    private fun validateAllQuestions(): Boolean {
        return question1RadioGroup.checkedRadioButtonId != -1 &&
                question2RadioGroup.checkedRadioButtonId != -1 &&
                question3RadioGroup.checkedRadioButtonId != -1 &&
                question4RadioGroup.checkedRadioButtonId != -1 &&
                question5RadioGroup.checkedRadioButtonId != -1 &&
                question6RadioGroup.checkedRadioButtonId != -1
    }

    private fun calculateRiskScore(): Int {
        var score = 0

        // Pregunta 1: Sangre en orina (muy importante)
        score += getQuestionScore(question1RadioGroup, 3)

        // Pregunta 2: Hinchazón (muy importante)
        score += getQuestionScore(question2RadioGroup, 3)

        // Pregunta 3: Presión arterial (importante)
        score += getQuestionScore(question3RadioGroup, 2)

        // Pregunta 4: Disminución de orina (muy importante)
        score += getQuestionScore(question4RadioGroup, 3)

        // Pregunta 5: Infección reciente (importante)
        score += getQuestionScore(question5RadioGroup, 2)

        // Pregunta 6: Fatiga (moderadamente importante)
        score += getQuestionScore(question6RadioGroup, 1)

        return score
    }

    private fun getQuestionScore(radioGroup: RadioGroup, weight: Int): Int {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.q1Yes, R.id.q2Yes, R.id.q3Yes, R.id.q4Yes, R.id.q5Yes, R.id.q6Yes -> weight
            R.id.q1Sometimes, R.id.q2Sometimes, R.id.q3Sometimes, R.id.q4Sometimes, R.id.q5Sometimes, R.id.q6Sometimes -> weight / 2
            else -> 0
        }
    }

    private fun showResults(score: Int) {
        val (riskLevel, message, recommendation) = when {
            score >= 10 -> {
                Triple(
                    getString(R.string.high_risk),
                    "Su evaluación indica un alto riesgo de problemas renales.",
                    getString(R.string.recommendation_doctor)
                )
            }
            score >= 5 -> {
                Triple(
                    getString(R.string.moderate_risk),
                    "Su evaluación indica un riesgo moderado de problemas renales.",
                    getString(R.string.recommendation_monitor)
                )
            }
            else -> {
                Triple(
                    getString(R.string.low_risk),
                    "Su evaluación indica un bajo riesgo de problemas renales.",
                    getString(R.string.recommendation_healthy)
                )
            }
        }

        showResultDialog(riskLevel, message, recommendation)
    }

    private fun showResultDialog(riskLevel: String, message: String, recommendation: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.survey_results))

        val dialogMessage = """
            $riskLevel

            $message

            $recommendation

            ${getString(R.string.disclaimer_text)}
        """.trimIndent()

        builder.setMessage(dialogMessage)
        builder.setPositiveButton(getString(R.string.i_understand)) { dialog, _ ->
            dialog.dismiss()
            // Save survey results
            saveSurveyResults(riskLevel, message)
        }

        builder.setCancelable(false)
        builder.show()
    }

    private fun saveSurveyResults(riskLevel: String, message: String) {
        val prefs = getSharedPreferences("SurveyResults", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val timestamp = System.currentTimeMillis()
        editor.putString("survey_$timestamp", "Nivel: $riskLevel, Mensaje: $message")
        editor.apply()

        Toast.makeText(this, "Resultados guardados", Toast.LENGTH_SHORT).show()
    }
}