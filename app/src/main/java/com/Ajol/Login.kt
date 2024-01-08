package com.Ajol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : ComponentActivity() {

    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    lateinit var btnReset: Button
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        setContentView(R.layout.activity_login)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        // Retrieve data from Firebase
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val message = dataSnapshot.getValue(String::class.java)
                Log.d("FirebaseData", "Value is: $message")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FirebaseData", "Failed to read value.", error.toException())
            }
        })

        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val intentregister = Intent(this, Register::class.java)
            startActivity(intentregister)
        }

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)

        editTextEmail.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Handle Enter key press for username field
                editTextPassword.requestFocus()
                return@setOnKeyListener true
            }
            false
        }

        editTextPassword.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Handle Enter key press for password field
                handleLogin()
                return@setOnKeyListener true
            }
            false
        }
//
//        editTextEmail.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus && editTextEmail.text.isEmpty()) {
//                showToast("Username Tidak Boleh Kosong! Mohon isi username terlebih dahulu.")
//                editTextEmail.requestFocus()
//            } else if (hasFocus) {
//                showToast("Username Telah Diisi!")
//            }
//        }
//
//        editTextPassword.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus && editTextPassword.text.isEmpty()) {
//                showToast("Password Tidak Boleh Kosong! Mohon isi password terlebih dahulu.")
//                editTextPassword.requestFocus()
//            } else if (hasFocus) {
//                showToast("Password Telah Diisi!")
//            }
//        }

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)

        btnLogin = findViewById(R.id.btnLogin)
        // Set on Click Listener on Registration button
        btnLogin.setOnClickListener {
            // taking FirebaseAuth instance
            val auth: FirebaseAuth = FirebaseAuth.getInstance()

            val email = editTextEmail.text.toString()
            val pass = editTextPassword.text.toString()

            editTextEmail.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
            editTextPassword.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS

            editTextEmail.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)
            editTextPassword.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)

            // check pass
            if (email.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            }

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Registration successful
                    auth.currentUser
                    Toast.makeText(
                        this,
                        "Successfully Sign In",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intentlogin = Intent(this, MainActivity::class.java)
                    startActivity(intentlogin)
                } else {
                    Toast.makeText(
                        this,
                        "Sign In Failed!",
                        Toast.LENGTH_SHORT
                    ).show()// Registration failed
                    Log.w(
                        "Registration",
                        "createUserWithEmail:failure",
                        it.exception
                    )
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handleLogin() {
        // Implement your login logic here
        showToast("Logging in...")
    }
}
