package com.rdmm.expensetracker.databse

data class ExpenseState(
    val expenses: List<Expense> = emptyList(),
    val expenseName: String = "",
    val expenseSumm: String = "",
    val expenseComment: String = "",
    val expenseCurrency: String = "",
    val expenseDate: String = "",
    val expenseTime: String = "",
    val expenseType: String = "",
    val sortType: SortType = SortType.BY_DATE
)
