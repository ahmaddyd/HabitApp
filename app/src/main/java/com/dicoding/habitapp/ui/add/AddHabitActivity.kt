package com.dicoding.habitapp.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.ui.ViewModelFactory
import com.dicoding.habitapp.utils.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddHabitActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var viewModel: AddHabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        supportActionBar?.title = getString(R.string.add_habit)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AddHabitViewModel::class.java]

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val title = findViewById<EditText>(R.id.add_ed_title).text.toString()
                val minutesFocus =
                    findViewById<EditText>(R.id.add_ed_minutes_focus).text.toString().toLong()
                val startTime = findViewById<TextView>(R.id.add_tv_start_time).text.toString()
                val priorityLevel =
                    findViewById<Spinner>(R.id.sp_priority_level).selectedItem.toString()
                if (title.isNotEmpty()) {
                    val habit = Habit(
                        title = title,
                        minutesFocus = minutesFocus,
                        startTime = startTime,
                        priorityLevel = priorityLevel
                    )
                    viewModel.saveHabit(habit)
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePicker(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timePicker")
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        findViewById<TextView>(R.id.add_tv_start_time).text = dateFormat.format(calendar.time)
    }
}