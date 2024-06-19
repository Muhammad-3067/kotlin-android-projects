package com.rdmm.expensetracker.databse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Long = 0L,

    @ColumnInfo(name = "expense_type")
    val expenseType: String = "",

    @ColumnInfo(name = "expense_name")
    val expenseName: String = "",

    @ColumnInfo(name = "expense_summ")
    val expenseSumm: String = "",

    @ColumnInfo(name = "expense_comment")
    val expenseComment: String = "",

    @ColumnInfo(name = "expense_currency")
    val expenseCurrency: String = "",

    @ColumnInfo(name = "expense_date")
    val expenseDate: String = "",

    @ColumnInfo(name = "expense_time")
    val expenseTime: String = "",
)