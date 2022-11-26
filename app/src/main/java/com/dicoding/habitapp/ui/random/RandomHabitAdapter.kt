package com.dicoding.habitapp.ui.random

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit

class RandomHabitAdapter(
    private val onClick: (Habit) -> Unit
) : RecyclerView.Adapter<RandomHabitAdapter.PagerViewHolder>() {

    private val habitMap = LinkedHashMap<PageType, Habit>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(key: PageType, habit: Habit) {
        habitMap[key] = habit
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PagerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pager_item, parent, false)
        )

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val pageData = habitMap[key] ?: return
        holder.bind(key, pageData)
    }

    override fun getItemCount() = habitMap.size

    private fun getIndexKey(position: Int) = habitMap.keys.toTypedArray().getOrNull(position)

    enum class PageType {
        HIGH, MEDIUM, LOW
    }

    inner class PagerViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        //TODO 14 : Create view and bind data to item view
        private val textViewTitle: TextView = itemView.findViewById(R.id.pager_title)

        private val ivPriorityLevel: ImageView = itemView.findViewById(R.id.pager_priority_level)

        private val textViewStartTime: TextView = itemView.findViewById(R.id.pager_start_time)

        private val textViewMinutes: TextView = itemView.findViewById(R.id.pager_minutes)

        private val btnStartCountDown: Button = itemView.findViewById(R.id.button_open_count_down)

        fun bind(pageType: PageType, pageData: Habit) {
            textViewTitle.text = pageData.title

            textViewStartTime.text = pageData.startTime

            textViewMinutes.text = pageData.minutesFocus.toString()

            val colorPriority = when (pageType) {
                PageType.HIGH -> R.drawable.ic_priority_high
                PageType.MEDIUM -> R.drawable.ic_priority_medium
                PageType.LOW -> R.drawable.ic_priority_low
            }

            ivPriorityLevel.setImageResource(colorPriority)

            btnStartCountDown.setOnClickListener { onClick(pageData) }
        }
    }
}