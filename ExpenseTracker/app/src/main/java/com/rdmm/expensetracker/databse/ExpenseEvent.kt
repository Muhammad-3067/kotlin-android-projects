package com.rdmm.expensetracker.databse

sealed interface ExpenseEvent {
    object SaveExpense: ExpenseEvent
    data class DeleteExpense(val expense: Expense): ExpenseEvent
    data class SetExpenseType(val expenseType: String): ExpenseEvent
    data class SetExpenseName(val expenseName: String): ExpenseEvent
    data class SetExpenseSumm(val expenseSumm: String): ExpenseEvent
    data class SetExpenseComment(val expenseComment: String): ExpenseEvent
    data class SetExpenseCurrency(val expenseCurrency: String): ExpenseEvent
    data class SetExpenseDate(val expenseDate: String): ExpenseEvent
    data class SetExpenseTime(val expenseTime: String): ExpenseEvent
    data class SortExpenses(val sortType: SortType): ExpenseEvent
}