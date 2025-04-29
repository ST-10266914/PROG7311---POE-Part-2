package com.example.budgettrackerapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CategoryListActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var categoryDao: CategoryDao
    private lateinit var categoryListView: ListView
    private lateinit var etNewCategory: EditText
    private lateinit var btnAddCategory: Button
    private lateinit var adapter: ArrayAdapter<String>
    private var categories = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        db = AppDatabase.getDatabase(this)
        categoryDao = db.categoryDao()

        categoryListView = findViewById(R.id.categoryListView)
        etNewCategory = findViewById(R.id.etNewCategory)
        btnAddCategory = findViewById(R.id.btnAddCategory)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        categoryListView.adapter = adapter

        loadCategories()

        btnAddCategory.setOnClickListener {
            val categoryName = etNewCategory.text.toString().trim()
            if (categoryName.isNotEmpty()) {
                lifecycleScope.launch {
                    categoryDao.insertCategory(Category(name = categoryName))
                    etNewCategory.text.clear()
                    loadCategories()
                }
            }
        }
    }

    private fun loadCategories() {
        lifecycleScope.launch {
            val all = categoryDao.getAllCategories()
            categories.clear()
            categories.addAll(all.map { it.name })
            adapter.notifyDataSetChanged()
        }
    }
}