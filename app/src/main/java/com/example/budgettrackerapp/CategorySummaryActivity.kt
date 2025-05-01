package com.example.budgettrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.budgettrackerapp.data.dao.ExpenseDao
import com.example.budgettrackerapp.AppDatabase
import java.text.SimpleDateFormat
import java.util.*

class CategorySummaryActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var expenseDao: ExpenseDao
    private lateinit var btnViewSummary: Button
    private lateinit var tvSummaryResults: TextView
    private lateinit var etStartDate: EditText
    private lateinit var etEndDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_summary)

        db = AppDatabase.getDatabase(this)
        expenseDao = db.expenseDao()

        btnViewSummary = findViewById(R.id.btnViewSummary)
        tvSummaryResults = findViewById(R.id.tvSummaryResults)
        etStartDate = findViewById(R.id.etStartDate)
        etEndDate = findViewById(R.id.etEndDate)

        btnViewSummary.setOnClickListener {
            val startDateStr = etStartDate.text.toString().trim()
            val endDateStr = etEndDate.text.toString().trim()

            if (startDateStr.isNotEmpty() && endDateStr.isNotEmpty()) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val startDate = dateFormat.parse(startDateStr)?.time ?: 0L
                val endDate = dateFormat.parse(endDateStr)?.time ?: 0L

                viewSummary(startDate, endDate)
            } else {
                tvSummaryResults.text = "Please enter both start and end dates."
            }
        }
    }

    private fun viewSummary(startDate: Long, endDate: Long) {
        lifecycleScope.launch {
            try {
                // Get the summary of expenses by category for the date range
                val summary = expenseDao.getExpenseSumByCategory(startDate, endDate)

                // Use a StringBuilder to format the results
                val builder = StringBuilder()
                for ((categoryId, total) in summary) {
                    // Optionally, convert categoryId to a category name if needed
                    builder.append("Category ID: $categoryId\nTotal Spent: $total\n\n")
                }

                // Set the formatted results to a TextView (assumed to be tvSummaryResults)
                tvSummaryResults.text = builder.toString()
            } catch (e: Exception) {
                tvSummaryResults.text = "Failed to load summary: ${e.message}"
            }
        }
    }
}