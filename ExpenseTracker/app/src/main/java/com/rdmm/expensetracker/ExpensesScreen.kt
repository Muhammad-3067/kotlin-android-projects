package com.rdmm.expensetracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rdmm.expensetracker.databse.ExpenseEvent
import com.rdmm.expensetracker.databse.ExpenseState
import com.rdmm.expensetracker.databse.SortType

// Expenses() calls inside the Navigation.kt
@Composable
fun Expenses(
    state: ExpenseState,
    onEvent: (ExpenseEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SortType.values().forEach { sortType ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                onEvent(ExpenseEvent.SortExpenses(sortType))
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = state.sortType == sortType,
                            onClick = {
                                onEvent(ExpenseEvent.SortExpenses(sortType))
                            }
                        )
                        Text(text = sortType.name)
                    }
                }
            }
        }
        items(state.expenses){expense ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "${expense.expenseType} \n" +
                            "${expense.expenseName} \n" +
                            "${expense.expenseSumm} \n" +
                            "${expense.expenseComment} \n" +
                            "${expense.expenseCurrency} \n" +
                            "${expense.expenseDate} \n" +
                            "${expense.expenseTime} \n",
                        fontSize = 20.sp)
                }
                IconButton(onClick = {
                    onEvent(ExpenseEvent.DeleteExpense(expense))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete expense"
                    )
                }
            }
        }
    }
}

