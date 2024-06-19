package com.rdmm.expensetracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdmm.expensetracker.databse.Expense
import com.rdmm.expensetracker.databse.ExpenseDao
import com.rdmm.expensetracker.databse.ExpenseEvent
import com.rdmm.expensetracker.databse.ExpenseState
import com.rdmm.expensetracker.databse.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val dao: ExpenseDao
): ViewModel() {


    private val _sortType = MutableStateFlow(SortType.BY_DATE)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _expenses = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.BY_DATE -> dao.getExpenseOrderByDate()
                SortType.BY_TIME -> dao.getExpenseOrderByTime()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ExpenseState())
    val state = combine(_state, _sortType, _expenses) { state, sortType, expenses ->
        state.copy(
            expenses = expenses,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExpenseState())


    fun onEvent(event: ExpenseEvent) {
        when(event) {
            ExpenseEvent.SaveExpense -> {
                val expenseType = state.value.expenseType
                val expenseName = state.value.expenseName
                val expenseSumm = state.value.expenseSumm
                val expenseComment = state.value.expenseComment
                val expenseCurrency = state.value.expenseCurrency
                val expenseDate = state.value.expenseDate
                val expenseTime = state.value.expenseTime

                if (expenseType.isBlank() || expenseName.isBlank()
                    || expenseSumm.isBlank() || expenseComment.isBlank()
                    || expenseCurrency.isBlank() || expenseDate.isBlank()
                    || expenseTime.isBlank()){
                    println("aaaaaaaaabbbbbbbbbbbb blank()")
                    println("Type $expenseType \n" +
                            "Name $expenseName \n" +
                            "Summ $expenseSumm \n" +
                            "Comment $expenseComment \n" +
                            "Currency $expenseCurrency \n" +
                            "Date $expenseDate \n" +
                            "Time $expenseTime \n")
                    return
                }

                val expense = Expense(
                    expenseType = expenseType,
                    expenseName = expenseName,
                    expenseSumm = expenseSumm,
                    expenseComment = expenseComment,
                    expenseCurrency = expenseCurrency,
                    expenseDate = expenseDate,
                    expenseTime = expenseTime,
                )

                viewModelScope.launch {
                    dao.insert(expense)
                }
                _state.update { it.copy(
                    expenseType = "",
                    expenseName = "",
                    expenseSumm = "",
                    expenseComment = "",
                    expenseCurrency = "",
                    expenseDate = "",
                    expenseTime = "",
                ) }
            }
            is ExpenseEvent.DeleteExpense -> {
                viewModelScope.launch {
                    dao.delete(event.expense)
                }
            }
            is ExpenseEvent.SetExpenseComment -> {
                _state.update { it.copy(
                    expenseComment = event.expenseComment
                ) }
            }
            is ExpenseEvent.SetExpenseCurrency -> {
                _state.update { it.copy(
                    expenseCurrency = event.expenseCurrency
                ) }
            }
            is ExpenseEvent.SetExpenseDate -> {
                _state.update { it.copy(
                    expenseDate = event.expenseDate
                ) }
            }
            is ExpenseEvent.SetExpenseName -> {
                _state.update { it.copy(
                    expenseName = event.expenseName
                ) }
            }
            is ExpenseEvent.SetExpenseSumm -> {
                _state.update { it.copy(
                    expenseSumm = event.expenseSumm
                ) }
            }
            is ExpenseEvent.SetExpenseTime -> {
                _state.update { it.copy(
                    expenseTime = event.expenseTime
                ) }
            }
            is ExpenseEvent.SetExpenseType -> {
                _state.update { it.copy(
                    expenseType = event.expenseType
                ) }
            }

            is ExpenseEvent.SortExpenses -> {
                _sortType.value = event.sortType
            }
        }
    }
}