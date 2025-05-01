package com.example.budgettrackerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,

    val userId: String
)


    val totalAmount: Double = 0.0 // Default to 0.0 if no value is set
)

