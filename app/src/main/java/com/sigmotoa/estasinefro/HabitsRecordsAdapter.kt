package com.sigmotoa.estasinefro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class HabitsRecordsAdapter : RecyclerView.Adapter<HabitsRecordsAdapter.HabitRecordViewHolder>() {

    private var records: List<HabitRecord> = emptyList()

    fun updateRecords(newRecords: List<HabitRecord>) {
        records = newRecords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit_record, parent, false)
        return HabitRecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitRecordViewHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount(): Int = records.size

    class HabitRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val waterTextView: TextView = itemView.findViewById(R.id.waterTextView)
        private val saltTextView: TextView = itemView.findViewById(R.id.saltTextView)

        private val weightTextView: TextView = itemView.findViewById(R.id.weightTextView)
        private val urinationTextView: TextView = itemView.findViewById(R.id.urinationTextView)
        private val swellingTextView: TextView = itemView.findViewById(R.id.swellingTextView)

        fun bind(record: HabitRecord) {
            dateTextView.text = "📅 ${record.date}"
            timeTextView.text = "⏰ ${record.time}"
            waterTextView.text = "💧 ${record.waterIntake}ml"
            saltTextView.text = "🧂 ${record.saltIntake}g"

            weightTextView.text = "⚖️ ${record.weight}kg"
            urinationTextView.text = "🚽 ${record.urinationFrequency}x"
            swellingTextView.text = "🦵 ${record.swellingLevel}"

            // Set background color based on swelling level
            when (record.swellingLevel) {
                "Severo" -> {
                    itemView.setBackgroundResource(R.drawable.background_severe)
                }
                "Moderado" -> {
                    itemView.setBackgroundResource(R.drawable.background_moderate)
                }
                "Leve" -> {
                    itemView.setBackgroundResource(R.drawable.background_mild)
                }
                else -> {
                    itemView.setBackgroundResource(R.drawable.background_normal)
                }
            }
        }
    }
}