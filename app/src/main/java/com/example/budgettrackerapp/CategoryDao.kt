package com.example.budgettrackerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Update
    suspend fun updateCategory(category: Category): Int


    @Insert
    fun insertCategory(category: Category)

    @Query("SELECT * FROM Category WHERE userId = :userId")
    suspend fun getAllCategories(userId: String): List<Category>

    @Delete
    suspend fun deleteCategory(category: Category): Int

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<Category>





}