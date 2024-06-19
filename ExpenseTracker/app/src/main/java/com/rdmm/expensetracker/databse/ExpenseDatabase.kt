package com.rdmm.expensetracker.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao
    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getInstance(context: Context): ExpenseDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, ExpenseDatabase::class.java, "expense_database")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}