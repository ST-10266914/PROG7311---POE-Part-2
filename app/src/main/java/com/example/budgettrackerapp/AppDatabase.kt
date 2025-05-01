package com.example.budgettrackerapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.budgettrackerapp.data.dao.ExpenseDao
import com.example.budgettrackerapp.data.dao.CategoryDao
import com.example.budgettrackerapp.data.model.Expense
import com.example.budgettrackerapp.data.model.Category

@Database(entities = [Expense::class, Category::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    //Decaalring Database in Roomdb
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "budget_tracker_db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }
}
