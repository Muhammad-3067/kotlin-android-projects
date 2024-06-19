package com.rdmm.expensetracker

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rdmm.expensetracker.databse.ExpenseEvent
import com.rdmm.expensetracker.databse.ExpenseState
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.time.Duration.Companion.days

// AddExpenseScreen() calls inside the FragmentAppBar.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    innerPaddingValues: PaddingValues,
    onEvent: (ExpenseEvent) -> Unit,
    state: ExpenseState
) {



    // DatePicker
    var showDateDialog by remember { mutableStateOf(false) }
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMM yyyy")
                .format(pickedDate)
        }
    }

    // TimerPicker
    var selectedHour by remember { mutableIntStateOf(0) }
    var selectedMinute by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    val timeState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )
    var currentTime by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    // dropdown category values
    val expensesList = arrayOf("Доходы", "Расходы")
    var expandedExpense by remember { mutableStateOf(false) }
    var selectedTextExpense by remember { mutableStateOf(expensesList[0]) }

    // dropdown category values
    val context = LocalContext.current
    val coffeeDrinksF = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expandedF by remember { mutableStateOf(false) }
    var selectedTextF by remember { mutableStateOf(coffeeDrinksF[0]) }

    // dropdown currency values
    val coffeeDrinksS = arrayOf("RUB", "UZS", "USD", "EUR", "TJS", "KGS", "KZT")
    var expandedS by remember { mutableStateOf(false) }
    var selectedTextS by remember { mutableStateOf(coffeeDrinksS[0]) }

    // TextField summ values
//    var textField1 by remember { mutableStateOf("") }
//
//    // TextField comment values
//    var textField2 by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(innerPaddingValues)) {
        Card(modifier = Modifier.fillMaxWidth().padding(10.dp)) {

            // dropdown expense type
            ExposedDropdownMenuBox(
                expanded = expandedExpense,
                onExpandedChange = {
                    expandedExpense = !expandedExpense
                }
            ) {
                onEvent(ExpenseEvent.SetExpenseType(selectedTextExpense))
                TextField(
                    value = selectedTextExpense,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedS) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedExpense,
                    onDismissRequest = { expandedExpense = false }
                ) {
                    expensesList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedTextExpense = item
                                onEvent(ExpenseEvent.SetExpenseType(item))
                                expandedExpense = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

            // TextField summ values
            TextField(
                value = state.expenseSumm,
                onValueChange = {
                    onEvent(ExpenseEvent.SetExpenseSumm(it))
                },
                placeholder = {
                    Text(text = "Enter the summ")
                    },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 24.sp)
                )

            // dropdown category values
            ExposedDropdownMenuBox(
                expanded = expandedF,
                onExpandedChange = {
                    expandedF = !expandedF
                }
            ) {
                onEvent(ExpenseEvent.SetExpenseName(selectedTextF))
                TextField(
                    value = selectedTextF,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedF) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedF,
                    onDismissRequest = { expandedF = false }
                ) {
                    coffeeDrinksF.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedTextF = item
                                onEvent(ExpenseEvent.SetExpenseName(item))
                                expandedF = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

            // TextField comment values
            TextField(
                value = state.expenseComment,
                onValueChange = {
                    onEvent(ExpenseEvent.SetExpenseComment(it))
                    },
                label = {
                    Text(text = "Add a comment")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 17.sp)

            )

            // dropdown currency values
            ExposedDropdownMenuBox(
                expanded = expandedS,
                onExpandedChange = {
                    expandedS = !expandedS
                }
            ) {
                onEvent(ExpenseEvent.SetExpenseCurrency(selectedTextS))
                TextField(
                    value = selectedTextS,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedS) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedS,
                    onDismissRequest = { expandedS = false }
                ) {
                    coffeeDrinksS.forEach { item ->
                        println(state.expenseCurrency)
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                println("ээээээээээээээээээээээээээээээ: $item")
                                onEvent(ExpenseEvent.SetExpenseCurrency(item))
                                selectedTextS = item
                                expandedS = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

            // TimePicker
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = Color.LightGray.copy(alpha = .88f))
                            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePicker(state = timeState)
                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { showDialog = false }) {
                                Text(text = "ОТМЕНА")
                            }
                            TextButton(onClick = {
                                showDialog = false
                                selectedHour = timeState.hour
                                selectedMinute = timeState.minute
                                selectedTime = "${timeState.hour} : ${timeState.minute}"
                            }) {
                                Text(text = "OK")
                            }
                        }
                    }
                }
            }

            // DatePicker
            val dateDialogState = rememberMaterialDialogState()
            MaterialDialog(
                dialogState = dateDialogState,
                buttons = {
                    positiveButton(text = "OK")
                    negativeButton(text = "ОТМЕНА")
                }
            ) {
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "LocalDate.now()"
                ){
                    pickedDate = it
                }
            }

            // Date and Timer pickers
            Row(verticalAlignment = Alignment.CenterVertically) {
                val sdf = SimpleDateFormat("HH:mm")
                val currentDateAndTime = sdf.format(Date())
                currentTime = currentDateAndTime

                // DatePicker dropdownmenu
                ExposedDropdownMenuBox(
                    expanded = showDateDialog,
                    onExpandedChange = {
                        dateDialogState.show()
                        showDateDialog = !showDateDialog
                    }) {
                    onEvent(ExpenseEvent.SetExpenseDate(formattedDate))
                    TextField(
                        value = formattedDate,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDateDialog) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(0.55f),
                        textStyle = LocalTextStyle.current.copy(fontSize = 22.sp)
                    )
                }

                // TimePicker dropdownmenu
                onEvent(ExpenseEvent.SetExpenseTime(if(selectedTime != "") selectedTime else currentTime))
                ExposedDropdownMenuBox(
                    expanded = showDialog,
                    onExpandedChange = {
                        showDialog = !showDialog
                    }) {
                    TextField(
                        value = if(selectedTime != "") selectedTime else currentTime,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDialog) },
                        modifier = Modifier
                            .menuAnchor(),
                        textStyle = LocalTextStyle.current.copy(fontSize = 22.sp)
                    )
                }
            }
        }
    }
}