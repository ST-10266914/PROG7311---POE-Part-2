package com.example.budgettrackerapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import com.example.budgettrackerapp.data.model.Category
import com.example.budgettrackerapp.data.dao.CategoryDao
import com.example.budgettrackerapp.AppDatabase

class CategoryListActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var categoryDao: CategoryDao
    private lateinit var categoryListView: ListView
    private lateinit var etNewCategory: EditText
    private lateinit var btnAddCategory: Button
    private var categoryList = mutableListOf<Category>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        db = AppDatabase.getDatabase(this)
        categoryDao = db.categoryDao()

        categoryListView = findViewById(R.id.categoryListView)
        etNewCategory = findViewById(R.id.etNewCategory)
        btnAddCategory = findViewById(R.id.btnAddCategory)

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        adapter = CategoryAdapter(this, categoryList,
            onEdit = { category -> showEditDialog(category) },
            onDelete = { category -> deleteCategory(category) }
        )
        categoryListView.adapter = adapter

        loadCategories()

        btnAddCategory.setOnClickListener {
            val categoryName = etNewCategory.text.toString().trim()
            if (categoryName.isNotEmpty()) {
                lifecycleScope.launch {

                    try {
                        val existingCategory = categoryDao.getCategoryByName(categoryName)
                        if (existingCategory == null) {
                            categoryDao.insertCategory(Category(name = categoryName))
                            etNewCategory.text.clear()
                            loadCategories()
                            Toast.makeText(this@CategoryListActivity, "Category added successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@CategoryListActivity, "Category already exists!", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@CategoryListActivity, "Failed to add category: ${e.message}", Toast.LENGTH_SHORT).show()
                    }

                }
            } else {
                Toast.makeText(this@CategoryListActivity, "Category name cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCategories() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        lifecycleScope.launch {
            val all = categoryDao.getAllCategories(userId)
            val userCategories = categoryDao.getAllCategories(userId)
            categoryList.clear()
            categoryList.addAll(all)
            adapter.notifyDataSetChanged()
        }

    }


    private fun showEditDialog(category: Category) {
        val editText = EditText(this)
        editText.setText(category.name)

        AlertDialog.Builder(this)
            .setTitle("Edit Category")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    lifecycleScope.launch {
                        categoryDao.update(category.copy(name = newName))
                        loadCategories()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteCategory(category: Category) {
        AlertDialog.Builder(this)
            .setTitle("Delete Category")
            .setMessage("Are you sure you want to delete '${category.name}'?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    categoryDao.delete(category)
                    loadCategories()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}