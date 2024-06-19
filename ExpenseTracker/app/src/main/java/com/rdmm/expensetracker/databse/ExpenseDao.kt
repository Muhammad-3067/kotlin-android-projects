package com.rdmm.expensetracker.databse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Upsert
    suspend fun insert(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY expense_date ASC ")
    fun getExpenseOrderByDate(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table ORDER BY expense_time ASC")
    fun getExpenseOrderByTime(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table ORDER BY expenseId DESC")
    fun getAll(): Flow<List<Expense>>
}