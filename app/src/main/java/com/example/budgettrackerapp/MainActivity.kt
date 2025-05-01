package com.example.budgettrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnAddExpense: Button
    private lateinit var btnViewExpenses: Button
    private lateinit var btnManageCategories: Button
    private lateinit var btnCategorySummary: Button
    private lateinit var btnSettings: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddExpense = findViewById(R.id.btnAddExpense)
        btnViewExpenses = findViewById(R.id.btnViewExpenses)
        btnManageCategories = findViewById(R.id.btnManageCategory)
        btnCategorySummary = findViewById(R.id.btnCategorySummary)
        btnSettings = findViewById(R.id.btnSettings)
        btnLogout = findViewById(R.id.btnLogout)

        // Navigate to Add Expense screen
        btnAddExpense.setOnClickListener {
            try {
                val intent = Intent(this, ExpenseEntryActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to View Expenses screen
        btnViewExpenses.setOnClickListener {
            try {
                val intent = Intent(this, ExpenseListActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        btnManageCategories.setOnClickListener {
            try {
                val intent = Intent(this, CategoryListActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
            // Navigate to Category Summary screen
            btnCategorySummary.setOnClickListener {
                try {
                    val intent = Intent(this, CategorySummaryActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            Navigate to Settings screen
             btnSettings.setOnClickListener {
            try {
             val intent = Intent(this, SettingsActivity::class.java)
             startActivity(intent)
            } catch (e: Exception) {
              e.printStackTrace()
            /   Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
             }

        
        }
    }
}
