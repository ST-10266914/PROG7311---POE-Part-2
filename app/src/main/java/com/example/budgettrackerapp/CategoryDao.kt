package com.example.budgettrackerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface CategoryDao {
//For updating data in the roomdb table
    @Update
    suspend fun updateCategory(category: Category): Int

//For inserting data into the roomdb table
    @Insert
    fun insertCategory(category: Category)

//For retrieving all info from table
    @Query("SELECT * FROM Category WHERE userId = :userId")
    suspend fun getAllCategories(userId: String): List<Category>

    @Delete
    suspend fun deleteCategory(category: Category): Int

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<Category>





}
