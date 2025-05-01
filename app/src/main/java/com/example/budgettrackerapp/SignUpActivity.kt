package com.example.budgettrackerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        val signupEmailEditText = findViewById<EditText>(R.id.signupEmailEditText)
        val signupPasswordEditText = findViewById<EditText>(R.id.signupPasswordEditText)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val loginRedirectButton = findViewById<Button>(R.id.loginRedirectButton)

        signupButton.setOnClickListener {
            val email = signupEmailEditText.text.toString()
            val password = signupPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Signup failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        loginRedirectButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}