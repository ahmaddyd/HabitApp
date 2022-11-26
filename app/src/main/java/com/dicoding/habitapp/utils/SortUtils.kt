package com.dicoding.habitapp.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    fun getSorteredQuery(filter: HabitSortType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM habits ")
        when (filter) {
            HabitSortType.START_TIME -> {
                simpleQuery.append("ORDER BY time(startTime) ASC")
            }
            HabitSortType.MINUTES_FOCUS -> {
                simpleQuery.append("ORDER BY minutesFocus ASC")
            }
            HabitSortType.TITLE_NAME -> {
                simpleQuery.append("ORDER BY title ASC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}