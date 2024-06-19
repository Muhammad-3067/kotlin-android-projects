package com.rdmm.expensetracker

sealed class Screen(val route: String) {
    object AppBar : Screen("main_screen")
    object Expenses : Screen("expenses_screen")
    object Incomes : Screen("incomes_screen")
    object AddExpenseScreen : Screen("add_expense_screen")
}