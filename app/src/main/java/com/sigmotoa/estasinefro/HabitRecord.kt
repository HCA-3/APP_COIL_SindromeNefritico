package com.sigmotoa.estasinefro

// Data class for habit records
data class HabitRecord(
    val timestamp: Long,
    val date: String,
    val time: String,
    val waterIntake: Int,
    val saltIntake: Double,
    val weight: Double,
    val urinationFrequency: Int,
    val swellingLevel: String
) {
    // Add a toString method for debugging
    override fun toString(): String {
        return "HabitRecord(date=$date, time=$time, water=$waterIntake, salt=$saltIntake, weight=$weight, urination=$urinationFrequency, swelling=$swellingLevel)"
    }
}