package com.dicoding.habitapp.ui.add

import androidx.lifecycle.ViewModel
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.data.HabitRepository

class AddHabitViewModel(private val habitRepository: HabitRepository) : ViewModel() {
    fun saveHabit(habit: Habit) {
        habitRepository.insertHabit(habit)
    }
}