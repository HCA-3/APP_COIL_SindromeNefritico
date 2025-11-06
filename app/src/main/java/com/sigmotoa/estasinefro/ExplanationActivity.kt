package com.sigmotoa.estasinefro

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ExplanationActivity : BaseActivity() {

    private lateinit var backButton: ImageView
    private lateinit var conceptTitle: TextView
    private lateinit var conceptSubtitle: TextView
    private lateinit var conceptExplanation: TextView
    private lateinit var keyPointsContainer: LinearLayout
    private lateinit var relatedInfo: TextView
    private lateinit var learnMoreButton: TextView

    companion object {
        private const val EXTRA_CONCEPT_TYPE = "concept_type"

        fun start(context: Context, conceptType: String) {
            val intent = Intent(context, ExplanationActivity::class.java)
            intent.putExtra(EXTRA_CONCEPT_TYPE, conceptType)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation)

        initViews()
        setupClickListeners()
        loadConceptData()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        conceptTitle = findViewById(R.id.conceptTitle)
        conceptSubtitle = findViewById(R.id.conceptSubtitle)
        conceptExplanation = findViewById(R.id.conceptExplanation)
        keyPointsContainer = findViewById(R.id.keyPointsContainer)
        relatedInfo = findViewById(R.id.relatedInfo)
        learnMoreButton = findViewById(R.id.learnMoreButton)
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }

        learnMoreButton.setOnClickListener {
            // Abrir recurso externo o actividad relacionada
            openExternalResource()
        }
    }

    private fun loadConceptData() {
        val conceptType = intent.getStringExtra(EXTRA_CONCEPT_TYPE) ?: return

        // Asegurar que el botón "aprende más" sea visible por defecto
        learnMoreButton.visibility = View.VISIBLE

        when (conceptType) {
            "nephrotic_syndrome" -> loadNephroticSyndromeData()
            "proteinuria" -> loadProteinuriaData()
            "hypoalbuminemia" -> loadHypoalbuminemiaData()
            "hyperlipidemia" -> loadHyperlipidemiaData()
            "edema" -> loadEdemaData()
            "postinfectious_gn" -> loadPostinfectiousGnData()
            "glomerulus" -> loadGlomerulusData()
            "immune_complex" -> loadImmuneComplexData()
            "complement_c3" -> loadComplementC3Data()
            "hematuria" -> loadHematuriaData()
            "hypertension" -> loadHypertensionData()
            "corticosteroids" -> loadCorticosteroidsData()
            "diuretics" -> loadDiureticsData()
            "dialysis" -> loadDialysisData()
            "kidney_biopsy" -> loadKidneyBiopsyData()
            "urinalysis" -> loadUrinalysisData()
            "blood_test" -> loadBloodTestData()
        }
    }

    private fun loadNephroticSyndromeData() {
        conceptTitle.text = getString(R.string.what_is_nephrotic_syndrome)
        conceptSubtitle.text = "Síndrome Nefrítico"
        conceptExplanation.text = getString(R.string.nephrotic_syndrome_explanation)

        val keyPoints = listOf(
            "Pérdida de proteínas > 3.5g/día en orina",
            "Niveles bajos de albúmina en sangre",
            "Hinchazón (edema) generalizada",
            "Aumento de colesterol y triglicéridos"
        )

        val detailedInfo = """
            ${getString(R.string.ns_detailed_explanation)}

            ${getString(R.string.ns_symptoms)}
            ${getString(R.string.ns_symptom1)}
            ${getString(R.string.ns_symptom2)}
            ${getString(R.string.ns_symptom3)}
            ${getString(R.string.ns_symptom4)}
            ${getString(R.string.ns_symptom5)}
            ${getString(R.string.ns_symptom6)}
            ${getString(R.string.ns_symptom7)}
            ${getString(R.string.ns_symptom8)}

            ${getString(R.string.ns_laboratory_findings)}
            ${getString(R.string.ns_lab1)}
            ${getString(R.string.ns_lab2)}
            ${getString(R.string.ns_lab3)}
            ${getString(R.string.ns_lab4)}
            ${getString(R.string.ns_lab5)}

            ${getString(R.string.ns_causes)}
            ${getString(R.string.ns_cause1)}
            ${getString(R.string.ns_cause2)}
            ${getString(R.string.ns_cause3)}
            ${getString(R.string.ns_cause4)}
            ${getString(R.string.ns_cause5)}
            ${getString(R.string.ns_cause6)}

            ${getString(R.string.ns_diagnosis)}
            ${getString(R.string.ns_diag1)}
            ${getString(R.string.ns_diag2)}
            ${getString(R.string.ns_diag3)}
            ${getString(R.string.ns_diag4)}
            ${getString(R.string.ns_diag5)}
            ${getString(R.string.ns_diag6)}

            ${getString(R.string.ns_treatment)}
            ${getString(R.string.ns_treat1)}
            ${getString(R.string.ns_treat2)}
            ${getString(R.string.ns_treat3)}
            ${getString(R.string.ns_treat4)}
            ${getString(R.string.ns_treat5)}
            ${getString(R.string.ns_treat6)}
            ${getString(R.string.ns_treat7)}

            ${getString(R.string.ns_prognosis)}
            ${getString(R.string.ns_prog1)}
            ${getString(R.string.ns_prog2)}
            ${getString(R.string.ns_prog3)}
            ${getString(R.string.ns_prog4)}

            ${getString(R.string.ns_complications)}
            ${getString(R.string.ns_comp1)}
            ${getString(R.string.ns_comp2)}
            ${getString(R.string.ns_comp3)}
            ${getString(R.string.ns_comp4)}
            ${getString(R.string.ns_comp5)}

            ${getString(R.string.ns_prevention)}
            ${getString(R.string.ns_prev1)}
            ${getString(R.string.ns_prev2)}
            ${getString(R.string.ns_prev3)}
            ${getString(R.string.ns_prev4)}

            ${getString(R.string.nephrotic_syndrome_detailed_title)}

            ${getString(R.string.nephrotic_syndrome_pathophysiology)}
            ${getString(R.string.nephrotic_syndrome_path1)}
            ${getString(R.string.nephrotic_syndrome_path2)}
            ${getString(R.string.nephrotic_syndrome_path3)}
            ${getString(R.string.nephrotic_syndrome_path4)}
            ${getString(R.string.nephrotic_syndrome_path5)}
            ${getString(R.string.nephrotic_syndrome_path6)}
            ${getString(R.string.nephrotic_syndrome_path7)}
            ${getString(R.string.nephrotic_syndrome_path8)}

            ${getString(R.string.nephrotic_syndrome_classification)}
            ${getString(R.string.nephrotic_syndrome_class1)}
            ${getString(R.string.nephrotic_syndrome_class2)}
            ${getString(R.string.nephrotic_syndrome_class3)}
            ${getString(R.string.nephrotic_syndrome_class4)}
            ${getString(R.string.nephrotic_syndrome_class5)}
            ${getString(R.string.nephrotic_syndrome_class6)}

            ${getString(R.string.nephrotic_syndrome_detailed_symptoms)}
            ${getString(R.string.nephrotic_syndrome_symptom1)}
            ${getString(R.string.nephrotic_syndrome_symptom2)}
            ${getString(R.string.nephrotic_syndrome_symptom3)}
            ${getString(R.string.nephrotic_syndrome_symptom4)}
            ${getString(R.string.nephrotic_syndrome_symptom5)}
            ${getString(R.string.nephrotic_syndrome_symptom6)}
            ${getString(R.string.nephrotic_syndrome_symptom7)}
            ${getString(R.string.nephrotic_syndrome_symptom8)}

            ${getString(R.string.nephrotic_syndrome_laboratory_detailed)}
            ${getString(R.string.nephrotic_syndrome_lab1)}
            ${getString(R.string.nephrotic_syndrome_lab2)}
            ${getString(R.string.nephrotic_syndrome_lab3)}
            ${getString(R.string.nephrotic_syndrome_lab4)}
            ${getString(R.string.nephrotic_syndrome_lab5)}
            ${getString(R.string.nephrotic_syndrome_lab6)}
            ${getString(R.string.nephrotic_syndrome_lab7)}
            ${getString(R.string.nephrotic_syndrome_lab8)}

            ${getString(R.string.nephrotic_syndrome_differential)}
            ${getString(R.string.nephrotic_syndrome_diff1)}
            ${getString(R.string.nephrotic_syndrome_diff2)}
            ${getString(R.string.nephrotic_syndrome_diff3)}
            ${getString(R.string.nephrotic_syndrome_diff4)}
            ${getString(R.string.nephrotic_syndrome_diff5)}
            ${getString(R.string.nephrotic_syndrome_diff6)}

            ${getString(R.string.nephrotic_syndrome_treatment_detailed)}
            ${getString(R.string.nephrotic_syndrome_treat1)}
            ${getString(R.string.nephrotic_syndrome_treat2)}
            ${getString(R.string.nephrotic_syndrome_treat3)}
            ${getString(R.string.nephrotic_syndrome_treat4)}
            ${getString(R.string.nephrotic_syndrome_treat5)}
            ${getString(R.string.nephrotic_syndrome_treat6)}
            ${getString(R.string.nephrotic_syndrome_treat7)}
            ${getString(R.string.nephrotic_syndrome_treat8)}

            ${getString(R.string.nephrotic_syndrome_prognosis_detailed)}
            ${getString(R.string.nephrotic_syndrome_prog1)}
            ${getString(R.string.nephrotic_syndrome_prog2)}
            ${getString(R.string.nephrotic_syndrome_prog3)}
            ${getString(R.string.nephrotic_syndrome_prog4)}
            ${getString(R.string.nephrotic_syndrome_prog5)}
            ${getString(R.string.nephrotic_syndrome_prog6)}
            ${getString(R.string.nephrotic_syndrome_prog7)}
            ${getString(R.string.nephrotic_syndrome_prog8)}
        """.trimIndent()

        setupKeyPoints(keyPoints)
        relatedInfo.text = detailedInfo
    }

    private fun loadProteinuriaData() {
        conceptTitle.text = getString(R.string.what_is_proteinuria)
        conceptSubtitle.text = "Proteína en Orina"
        conceptExplanation.text = getString(R.string.proteinuria_explanation)

        val keyPoints = listOf(
            "Normal: < 150mg/día de proteína en orina",
            "Síndrome Nefrótico: > 3.5g/día",
            "Detectada mediante análisis de orina",
            "Signo de daño en los filtros renales"
        )

        val relatedText = "La proteinuria persistente es un factor de riesgo para progresión de enfermedad renal crónica."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadHypoalbuminemiaData() {
        conceptTitle.text = getString(R.string.what_is_hypoalbuminemia)
        conceptSubtitle.text = "Albúmina Baja"
        conceptExplanation.text = getString(R.string.hypoalbuminemia_explanation)

        val keyPoints = listOf(
            "Normal: 3.5-5.0 g/dL de albúmina",
            "En Síndrome Nefrótico: < 2.5 g/dL",
            "Causa principal de edema",
            "Afecta transporte de medicamentos"
        )

        val relatedText = "La hipoalbuminemia también puede ser causada por desnutrición, enfermedad hepática o inflamación crónica."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadHyperlipidemiaData() {
        conceptTitle.text = getString(R.string.what_is_hyperlipidemia)
        conceptSubtitle.text = "Grasas Elevadas en Sangre"
        conceptExplanation.text = getString(R.string.hyperlipidemia_explanation)

        val keyPoints = listOf(
            "Colesterol LDL y triglicéridos elevados",
            "Respuesta del hígado a pérdida de proteínas",
            "Aumenta riesgo cardiovascular",
            "Mejora con tratamiento del síndrome"
        )

        val relatedText = "El hígado aumenta la producción de lipoproteínas para compensar la pérdida de proteínas."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadEdemaData() {
        conceptTitle.text = getString(R.string.what_is_edema)
        conceptSubtitle.text = "Hinchazón"
        conceptExplanation.text = getString(R.string.edema_explanation)

        val keyPoints = listOf(
            "Comienza en párpados y cara",
            "Progresivo a piernas y genitales",
            "Puede causar aumento de peso rápido",
            "Signo de retención de líquidos"
        )

        val relatedText = "El edema puede ser periférico (extremidades) o generalizado (anasarca) en casos severos."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText

        // Ocultar el botón "aprende más" para el edema ya que no hay más información interna
        learnMoreButton.visibility = View.GONE
    }

    private fun loadPostinfectiousGnData() {
        conceptTitle.text = getString(R.string.what_is_postinfectious_gn)
        conceptSubtitle.text = "GN Postinfecciosa"
        conceptExplanation.text = getString(R.string.postinfectious_gn_explanation)

        val keyPoints = listOf(
            "Ocurre 1-3 semanas después de infección",
            "Comúnmente por estreptococo",
            "Afecta principalmente niños",
            "Generalmente autolimitada"
        )

        val detailedInfo = """
            ${getString(R.string.postinfectious_gn_detailed_title)}

            ${getString(R.string.postinfectious_gn_pathophysiology)}
            ${getString(R.string.postinfectious_gn_path1)}
            ${getString(R.string.postinfectious_gn_path2)}
            ${getString(R.string.postinfectious_gn_path3)}
            ${getString(R.string.postinfectious_gn_path4)}
            ${getString(R.string.postinfectious_gn_path5)}
            ${getString(R.string.postinfectious_gn_path6)}
            ${getString(R.string.postinfectious_gn_path7)}
            ${getString(R.string.postinfectious_gn_path8)}

            ${getString(R.string.postinfectious_gn_epidemiology)}
            ${getString(R.string.postinfectious_gn_epi1)}
            ${getString(R.string.postinfectious_gn_epi2)}
            ${getString(R.string.postinfectious_gn_epi3)}
            ${getString(R.string.postinfectious_gn_epi4)}
            ${getString(R.string.postinfectious_gn_epi5)}
            ${getString(R.string.postinfectious_gn_epi6)}

            ${getString(R.string.postinfectious_gn_symptoms_detailed)}
            ${getString(R.string.postinfectious_gn_symptom1)}
            ${getString(R.string.postinfectious_gn_symptom2)}
            ${getString(R.string.postinfectious_gn_symptom3)}
            ${getString(R.string.postinfectious_gn_symptom4)}
            ${getString(R.string.postinfectious_gn_symptom5)}
            ${getString(R.string.postinfectious_gn_symptom6)}
            ${getString(R.string.postinfectious_gn_symptom7)}
            ${getString(R.string.postinfectious_gn_symptom8)}
            ${getString(R.string.postinfectious_gn_symptom9)}
            ${getString(R.string.postinfectious_gn_symptom10)}

            ${getString(R.string.postinfectious_gn_laboratory)}
            ${getString(R.string.postinfectious_gn_lab1)}
            ${getString(R.string.postinfectious_gn_lab2)}
            ${getString(R.string.postinfectious_gn_lab3)}
            ${getString(R.string.postinfectious_gn_lab4)}
            ${getString(R.string.postinfectious_gn_lab5)}
            ${getString(R.string.postinfectious_gn_lab6)}
            ${getString(R.string.postinfectious_gn_lab7)}
            ${getString(R.string.postinfectious_gn_lab8)}
            ${getString(R.string.postinfectious_gn_lab9)}
            ${getString(R.string.postinfectious_gn_lab10)}

            ${getString(R.string.postinfectious_gn_histology)}
            ${getString(R.string.postinfectious_gn_hist1)}
            ${getString(R.string.postinfectious_gn_hist2)}
            ${getString(R.string.postinfectious_gn_hist3)}
            ${getString(R.string.postinfectious_gn_hist4)}
            ${getString(R.string.postinfectious_gn_hist5)}
            ${getString(R.string.postinfectious_gn_hist6)}
            ${getString(R.string.postinfectious_gn_hist7)}

            ${getString(R.string.postinfectious_gn_treatment_detailed)}
            ${getString(R.string.postinfectious_gn_treat1)}
            ${getString(R.string.postinfectious_gn_treat2)}
            ${getString(R.string.postinfectious_gn_treat3)}
            ${getString(R.string.postinfectious_gn_treat4)}
            ${getString(R.string.postinfectious_gn_treat5)}
            ${getString(R.string.postinfectious_gn_treat6)}
            ${getString(R.string.postinfectious_gn_treat7)}
            ${getString(R.string.postinfectious_gn_treat8)}

            ${getString(R.string.postinfectious_gn_complications)}
            ${getString(R.string.postinfectious_gn_comp1)}
            ${getString(R.string.postinfectious_gn_comp2)}
            ${getString(R.string.postinfectious_gn_comp3)}
            ${getString(R.string.postinfectious_gn_comp4)}
            ${getString(R.string.postinfectious_gn_comp5)}
            ${getString(R.string.postinfectious_gn_comp6)}
            ${getString(R.string.postinfectious_gn_comp7)}
            ${getString(R.string.postinfectious_gn_comp8)}

            ${getString(R.string.postinfectious_gn_prognosis)}
            ${getString(R.string.postinfectious_gn_prog1)}
            ${getString(R.string.postinfectious_gn_prog2)}
            ${getString(R.string.postinfectious_gn_prog3)}
            ${getString(R.string.postinfectious_gn_prog4)}
            ${getString(R.string.postinfectious_gn_prog5)}
            ${getString(R.string.postinfectious_gn_prog6)}
            ${getString(R.string.postinfectious_gn_prog7)}
            ${getString(R.string.postinfectious_gn_prog8)}
        """.trimIndent()

        setupKeyPoints(keyPoints)
        relatedInfo.text = detailedInfo
    }

    private fun loadGlomerulusData() {
        conceptTitle.text = getString(R.string.what_is_glomerulus)
        conceptSubtitle.text = "Filtro Renal"
        conceptExplanation.text = getString(R.string.glomerulus_explanation)

        val keyPoints = listOf(
            "Unidad básica de filtración renal",
            "Aproximadamente 1 millón por riñón",
            "Filtra 180L de sangre diariamente",
            "Selectivo: retiene proteínas"
        )

        val relatedText = "El daño glomerular es la base de muchas enfermedades renales primarias."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadImmuneComplexData() {
        conceptTitle.text = getString(R.string.what_is_immune_complex)
        conceptSubtitle.text = "Complejos Inmunes"
        conceptExplanation.text = getString(R.string.immune_complex_explanation)

        val keyPoints = listOf(
            "Anticuerpo + antígeno = complejo inmune",
            "Normalmente eliminados por fagocitos",
            "En GN: se depositan en glomérulos",
            "Activan sistema del complemento"
        )

        val relatedText = "Los complejos inmunes también se encuentran en lupus, artritis reumatoide y otras enfermedades autoinmunes."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadComplementC3Data() {
        conceptTitle.text = getString(R.string.what_is_complement_c3)
        conceptSubtitle.text = "Proteína del Sistema Inmune"
        conceptExplanation.text = getString(R.string.complement_c3_explanation)

        val keyPoints = listOf(
            "Normal: 90-180 mg/dL",
            "En GN postinfecciosa: bajo",
            "Se consume durante inflamación",
            "Se normaliza en 6-8 semanas"
        )

        val relatedText = "El complemento C3 es parte del sistema inmune innato y ayuda a eliminar patógenos."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadHematuriaData() {
        conceptTitle.text = getString(R.string.what_is_hematuria)
        conceptSubtitle.text = "Sangre en Orina"
        conceptExplanation.text = getString(R.string.hematuria_explanation)

        val keyPoints = listOf(
            "Macroscópica: visible a simple vista",
            "Microscópica: solo en microscopio",
            "Signo de inflamación glomerular",
            "Puede causar orina color té"
        )

        val relatedText = "La hematuria glomerular se caracteriza por dismorfia eritrocitaria y cilindros hemáticos."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadHypertensionData() {
        conceptTitle.text = getString(R.string.what_is_hypertension)
        conceptSubtitle.text = "Presión Arterial Elevada"
        conceptExplanation.text = getString(R.string.hypertension_explanation)

        val keyPoints = listOf(
            "Retención de sodio y agua",
            "Aumento de volumen sanguíneo",
            "Activación del sistema renina-angiotensina",
            "Requiere tratamiento específico"
        )

        val relatedText = "La hipertensión acelera el daño renal y aumenta riesgo cardiovascular."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadCorticosteroidsData() {
        conceptTitle.text = getString(R.string.what_is_corticosteroids)
        conceptSubtitle.text = "Esteroides"
        conceptExplanation.text = getString(R.string.corticosteroids_explanation)

        val keyPoints = listOf(
            "Prednisona: 1-2 mg/kg/día",
            "Reduce permeabilidad glomerular",
            "Efectos secundarios significativos",
            "Tratamiento de primera línea"
        )

        val relatedText = "Los esteroides pueden causar aumento de peso, osteoporosis, diabetes y supresión adrenal."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadDiureticsData() {
        conceptTitle.text = getString(R.string.what_is_diuretics)
        conceptSubtitle.text = "Pastillas para Orinar"
        conceptExplanation.text = getString(R.string.diuretics_explanation)

        val keyPoints = listOf(
            "Furosemida: más potente",
            "Espironolactona: ahorra potasio",
            "Controlan edema e hipertensión",
            "Monitorear electrolitos"
        )

        val relatedText = "Los diuréticos pueden causar deshidratación, desequilibrio electrolítico y hipotensión."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadDialysisData() {
        conceptTitle.text = getString(R.string.what_is_dialysis)
        conceptSubtitle.text = "Tratamiento Renal"
        conceptExplanation.text = getString(R.string.dialysis_explanation)

        val keyPoints = listOf(
            "Hemodiálisis: filtro artificial",
            "Diálisis peritoneal: usa abdomen",
            "Generalmente temporal en niños",
            "3-4 sesiones por semana"
        )

        val relatedText = "La diálisis se necesita cuando hay falla renal aguda o crónica avanzada."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadKidneyBiopsyData() {
        conceptTitle.text = getString(R.string.what_is_kidney_biopsy)
        conceptSubtitle.text = "Muestra Renal"
        conceptExplanation.text = getString(R.string.kidney_biopsy_explanation)

        val keyPoints = listOf(
            "Procedimiento con anestesia local",
            "Obtiene 1-2 cilindros de tejido",
            "Estudio con microscopio electrónico",
            "Define tratamiento específico"
        )

        val relatedText = "La biopsia tiene riesgos de sangrado y requiere reposo absoluto post-procedimiento."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadUrinalysisData() {
        conceptTitle.text = getString(R.string.what_is_urinalysis)
        conceptSubtitle.text = "Análisis de Orina"
        conceptExplanation.text = getString(R.string.urinalysis_explanation)

        val keyPoints = listOf(
            "Tira reactiva: screening inicial",
            "Sedimento: células y cilindros",
            "Proteína/creatinina: cuantificación",
            "No invasivo y económico"
        )

        val relatedText = "El análisis de orina es fundamental para diagnóstico y seguimiento de enfermedades renales."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun loadBloodTestData() {
        conceptTitle.text = getString(R.string.what_is_blood_test)
        conceptSubtitle.text = "Análisis de Sangre"
        conceptExplanation.text = getString(R.string.blood_test_explanation)

        val keyPoints = listOf(
            "Creatinina: función renal",
            "BUN: producto de desecho",
            "Albúmina: proteína sérica",
            "Colesterol: lípidos en sangre"
        )

        val relatedText = "Los análisis de sangre permiten evaluar la gravedad y respuesta al tratamiento."

        setupKeyPoints(keyPoints)
        relatedInfo.text = relatedText
    }

    private fun setupKeyPoints(points: List<String>) {
        keyPointsContainer.removeAllViews()

        points.forEach { point ->
            val pointView = createKeyPointView(point)
            keyPointsContainer.addView(pointView)
        }
    }

    private fun createKeyPointView(point: String): View {
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.setPadding(0, 8, 0, 8)

        val bullet = TextView(this)
        bullet.text = "•"
        bullet.textSize = 16f
        bullet.setTextColor(ContextCompat.getColor(this, R.color.blue_marble))
        bullet.setPadding(0, 0, 12, 0)

        val textView = TextView(this)
        textView.text = point
        textView.textSize = 14f
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))

        linearLayout.addView(bullet)
        linearLayout.addView(textView)

        return linearLayout
    }

    private fun openExternalResource() {
        // Abrir recurso educativo externo
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse("https://www.kidney.org/")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Si no hay navegador, mostrar mensaje informativo
                androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Recurso Externo")
                    .setMessage("Para acceder a recursos adicionales, visite: https://www.kidney.org/")
                    .setPositiveButton("OK", null)
                    .show()
            }
        } catch (e: Exception) {
            // Manejar cualquier excepción que pueda ocurrir
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("No se pudo abrir el recurso externo.")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}