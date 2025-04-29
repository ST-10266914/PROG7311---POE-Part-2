package com.example.budgettrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnManageCategory: Button
    private lateinit var btnCategorySummary: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnManageCategory = findViewById(R.id.btnManageCategory)
        btnCategorySummary = findViewById(R.id.btnCategorySummary)


        btnManageCategory.setOnClickListener {
            startActivity(Intent(this, CategoryListActivity::class.java))
        }

        btnCategorySummary.setOnClickListener {
            startActivity(Intent(this, CategorySummaryActivity::class.java))
        }
    }
}