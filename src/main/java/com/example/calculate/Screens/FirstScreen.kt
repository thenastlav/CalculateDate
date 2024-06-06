package com.example.calculate.Screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calculate.Navigation.Screen
import com.example.calculate.R
import com.example.calculate.ui.theme.MainViewModel
import com.example.calculate.ui.theme.Model
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun DateDo(navController: NavHostController, modifier: Modifier = Modifier) {
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
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.start),
                    modifier = Modifier.weight(0.55f),
                    fontSize = 25.sp
                )
                Text(
                    text = mvm.date1.format("dd.MM.yyyy"),
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
                    mvm.date1 = formattedDate
                }) {
                    Text(stringResource(id = R.string.current))
                }
                Button(onClick = {
                    showDatePicker {
                        val formattedDate = it.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        mvm.date1 = formattedDate }
                }) {
                    Text(stringResource(id = R.string.select))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.end),
                    modifier = Modifier.weight(0.55f),
                    fontSize = 25.sp
                )
                Text(
                    text = mvm.date2.format("dd.MM.yyyy"),
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
                    mvm.date2 = formattedDate
                }) {
                    Text(stringResource(id = R.string.current))
                }
                Button(onClick = {
                    showDatePicker {
                        val formattedDate = it.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        mvm.date2 = formattedDate }
                }) {
                    Text(stringResource(id = R.string.select))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {mvm.rez = model.calculateInterval(mvm.convertStringToDate(mvm.date1), mvm.convertStringToDate(mvm.date2)) }
            ) {
                Text(stringResource(id = R.string.first_screen))
            }
            Text(
                text = mvm.rez,
                fontSize = 25.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            FilledIconButton(onClick = {navController.navigate(Screen.SECOND_SCREEN.route)}) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_forward),
                    contentDescription = stringResource(id = R.string.button_next)
                )
            }
        }
    }

}


