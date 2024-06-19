package com.rdmm.expensetracker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rdmm.expensetracker.databse.ExpenseEvent
import com.rdmm.expensetracker.databse.ExpenseState

// Navigation calls inside the MainActivity.kt
@Composable
fun Navigation(
    navController: NavHostController,
    onEvent: (ExpenseEvent) -> Unit,
    state: ExpenseState) {
    NavHost(navController = navController, startDestination = Screen.AppBar.route) {
        composable(route = Screen.AppBar.route) {
            AppBar(navController)
        }
        composable(route = Screen.Expenses.route) {
            Expenses(state, onEvent)
        }
        composable(route = Screen.Incomes.route) {
            Incomes()
        }
        composable(route = Screen.AddExpenseScreen.route) {
            FragmentAppBar({navController.navigate(Screen.AppBar.route)}, onEvent, state)
        }
    }
}