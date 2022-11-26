package com.dicoding.habitapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.data.HabitRepository

class DetailHabitViewModel(private val habitRepository: HabitRepository) : ViewModel() {

    private val _habitId = MutableLiveData<Int>()

    private val _habit = _habitId.switchMap { id ->
        habitRepository.getHabitById(id)
    }
    val habit: LiveData<Habit> = _habit

    fun start(habitId: Int?) {
        if (habitId == _habitId.value) {
            return
        }
        _habitId.value = habitId!!
    }

}