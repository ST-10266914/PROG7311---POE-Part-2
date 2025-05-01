package com.example.budgettrackerapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {

    @Insert
    fun insertCategory(category: Category)

    @Query("SELECT * FROM Category WHERE userId = :userId")
    suspend fun getAllCategories(userId: String): List<Category>

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

}