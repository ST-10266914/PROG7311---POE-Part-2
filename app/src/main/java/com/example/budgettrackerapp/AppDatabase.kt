package com.example.budgettrackerapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Add Expense::class the line below where Category::class is when doing expense entry
@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {


//Uncomment abstract fun expenseDao(): ExpenseDao when you doing expense Devesh
    abstract fun categoryDao(): CategoryDao
//    abstract fun expenseDao(): ExpenseDao


    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "budget_tracker_db"
                ).build()
            }
            return INSTANCE!!
        }
    }
}