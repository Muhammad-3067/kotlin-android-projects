package com.rdmm.expensetracker

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.rdmm.expensetracker.databse.ExpenseEvent
import com.rdmm.expensetracker.databse.ExpenseState

// FragmentAppBar calls inside the Navigation.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FragmentAppBar(
    backToMainScreen: () -> Unit,
    onEvent: (ExpenseEvent) -> Unit,
    state: ExpenseState
) {
    val context = LocalContext.current
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Add Expense",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { backToMainScreen() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        backToMainScreen()
                        onEvent(ExpenseEvent.SaveExpense)
                        Toast.makeText(context, "Your expense added", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Localized description"
                        )
                    }
                },
//                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        AddExpenseScreen(innerPadding, onEvent, state)
    }
}