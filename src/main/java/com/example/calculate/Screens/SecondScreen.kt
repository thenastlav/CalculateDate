package com.example.calculate.Screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calculate.R
import com.example.calculate.ui.theme.MainViewModel
import com.example.calculate.ui.theme.Model
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun DateDo2(navController: NavHostController, modifier: Modifier = Modifier) {
    val mvm: MainViewModel = viewModel()
    val model = Model()

    val context = LocalContext.current

    fun showDatePicker(onDateSet: (LocalDate) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                onDateSet(LocalDate.of(year, month + 1, dayOfMonth))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(id = R.string.start),
                    modifier = Modifier.weight(0.55f),
                    fontSize = 25.sp
                )
                Text(
                    text = mvm.date3.format("dd.MM.yyyy"),
                    modifier = Modifier.weight(0.45f),
                    fontSize = 25.sp
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    val currentDate = LocalDate.now()
                    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    mvm.date3 = formattedDate
                }) {
                    Text(stringResource(id = R.string.current))
                }
                Button(onClick = {
                    showDatePicker {
                        val formattedDate = it.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        mvm.date3 = formattedDate }
                }) {
                    Text(stringResource(id = R.string.select))
                }
            }
        }

        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.years), fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(0.50f)
            )
            OutlinedTextField(
                value = mvm.years, onValueChange = {mvm.years = it },
                modifier = Modifier.weight(0.50f).background(Color.LightGray),
                textStyle = TextStyle(fontSize = 25.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.month), fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(0.50f)
            )
            OutlinedTextField(
                value = mvm.months, onValueChange = {mvm.months = it },
                modifier = Modifier
                    .weight(0.50f)
                    .background(Color.LightGray),
                textStyle = TextStyle(fontSize = 25.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.week), fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(0.50f)
            )
            OutlinedTextField(
                value = mvm.weeks, onValueChange = {mvm.weeks = it },
                modifier = Modifier
                    .weight(0.50f)
                    .background(Color.LightGray),
                textStyle = TextStyle(fontSize = 25.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }

        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.days), fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier.weight(0.50f)
            )
            OutlinedTextField(
                value = mvm.days, onValueChange = {mvm.days = it },
                modifier = Modifier
                    .weight(0.50f)
                    .background(Color.LightGray),
                textStyle = TextStyle(fontSize = 25.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                mvm.rez1 = model.calculateNewDate(mvm.convertStringToDate(mvm.date3),
                    mvm.years.toInt(), mvm.months.toInt(),mvm.weeks.toInt(), mvm.days.toInt()) }) {
                Text( stringResource(id = R.string.newDate))
            }
            Text(
                text = mvm.rez1,
                fontSize = 25.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        FilledIconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_back),
                contentDescription = stringResource(id = R.string.back)
            )
        }
    }

}