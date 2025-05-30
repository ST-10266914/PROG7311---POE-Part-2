package com.example.budgettrackerapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    private lateinit var etMinGoal: EditText
    private lateinit var etMaxGoal: EditText
    private lateinit var btnSaveGoals: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        etMinGoal = findViewById(R.id.etMinGoal)
        etMaxGoal = findViewById(R.id.etMaxGoal)
        btnSaveGoals = findViewById(R.id.btnSaveGoals)

        loadGoals()

        btnSaveGoals.setOnClickListener {
            saveGoals()
        }
    }

//For loading the min/max goals created
    private fun loadGoals() {
        val sharedPref = getSharedPreferences("goals", Context.MODE_PRIVATE)
        val minGoal = sharedPref.getFloat("minGoal", 0f)
        val maxGoal = sharedPref.getFloat("maxGoal", 0f)
        etMinGoal.setText(minGoal.toString())
        etMaxGoal.setText(maxGoal.toString())
    }

    //For saving the goals after entering the info
    private fun saveGoals() {
        val minGoal = etMinGoal.text.toString().toFloatOrNull() ?: 0f
        val maxGoal = etMaxGoal.text.toString().toFloatOrNull() ?: 0f

        val sharedPref = getSharedPreferences("goals", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putFloat("minGoal", minGoal)
            putFloat("maxGoal", maxGoal)
            apply()
        }

        Toast.makeText(this, "Goals saved!", Toast.LENGTH_SHORT).show()
    }

}
