package com.example.calculate.ui.theme

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Model {
    fun calculateInterval(dat1: LocalDate, dat2: LocalDate):String{

        val year = ChronoUnit.YEARS.between(dat1, dat2)
        val dat2WithoutYears = dat2.minusYears(year)

        val month = ChronoUnit.MONTHS.between(dat1, dat2WithoutYears)
        val dat2WithoutMonths = dat2WithoutYears.minusMonths(month)

        val week = ChronoUnit.WEEKS.between(dat1, dat2WithoutMonths)
        val dat2WithoutWeek= dat2WithoutMonths.minusWeeks(week)

        val day = ChronoUnit.DAYS.between(dat1, dat2WithoutWeek)

        val str= "Interval $year years $month month $week weeks $day days "
        return str
    }

    fun calculateNewDate(dat1: LocalDate, years: Int, months: Int, weeks: Int, days: Int): String {
        var newDate = dat1.plusYears(years.toLong())
        newDate = newDate.plusMonths(months.toLong())
        newDate = newDate.plusWeeks(weeks.toLong())
        newDate = newDate.plusDays(days.toLong())

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate = newDate.format(formatter)

        return "New date $formattedDate"
    }
}