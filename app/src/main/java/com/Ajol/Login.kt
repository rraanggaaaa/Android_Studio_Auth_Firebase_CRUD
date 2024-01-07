package com.Ajol

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Login : ComponentActivity() {

    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    lateinit var editTextUsername: EditText
    lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        setContentView(R.layout.activity_login)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intentlogin = Intent(this, MainActivity::class.java)
            startActivity(intentlogin)
        }

        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val intentregister = Intent(this, Register::class.java)
            startActivity(intentregister)
        }

        editTextUsername = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)

        editTextUsername.setOnKeyListener { _, keyCode, event ->
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

        editTextUsername.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && editTextUsername.text.isEmpty()) {
                showToast("Username Tidak Boleh Kosong! Mohon isi username terlebih dahulu.")
                editTextUsername.requestFocus()
            } else if (hasFocus) {
                showToast("Username Telah Diisi!")
            }
        }

        editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && editTextPassword.text.isEmpty()) {
                showToast("Password Tidak Boleh Kosong! Mohon isi password terlebih dahulu.")
                editTextPassword.requestFocus()
            } else if (hasFocus) {
                showToast("Password Telah Diisi!")
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
