package com.example.budgettrackerapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CategorySummaryActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
//Devesh you must Uncomment the line of code below when you doing expense
//    private lateinit var expenseDao: ExpenseDao


    private lateinit var btnViewSummary: Button
    private lateinit var tvSummaryResults: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_summary)

        db = AppDatabase.getDatabase(this)
//Even this line of code below here
//        expenseDao = db.expenseDao()


        btnViewSummary = findViewById(R.id.btnViewSummary)
        tvSummaryResults = findViewById(R.id.tvSummaryResults)


//And this

//        btnViewSummary.setOnClickListener {
//            viewSummary()
//        }
    }

//And this last function here

//    private fun viewSummary() {
//        lifecycleScope.launch {
//            val summary = expenseDao.getExpenseSumByCategory(startDate, endDate)
//            val builder = StringBuilder()
//            for ((category, total) in summary) {
//                builder.append("Category: $category\nTotal Spent: $total\n\n")
//            }
//            tvSummaryResults.text = builder.toString()
//        }
//    }
}