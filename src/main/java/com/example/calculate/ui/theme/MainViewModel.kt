package com.example.calculate.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar

class MainViewModel: ViewModel() {

    var date1 by mutableStateOf("")
    var date2 by mutableStateOf("")
    var rez by mutableStateOf("")

    var rez1 by  mutableStateOf("")
    var date3 by mutableStateOf("")

    var years by mutableStateOf("0")
    var months by  mutableStateOf("0")
    var weeks by  mutableStateOf("0")
    var days by mutableStateOf("0")

    fun convertStringToDate(dateString: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return LocalDate.parse(dateString, formatter)

    }
}