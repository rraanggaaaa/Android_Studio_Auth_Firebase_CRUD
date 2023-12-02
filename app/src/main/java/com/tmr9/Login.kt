package com.tmr9

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.tmr9.R.*

class Login : ComponentActivity() {

    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    lateinit var editTextUsername: EditText
    lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        setContentView(layout.activity_login)

        btnLogin = findViewById(id.btnLogin)
        btnLogin.setOnClickListener {
            val intentlogin = Intent(this, MainActivity::class.java)
            startActivity(intentlogin)
        }

        btnRegister = findViewById(id.btnRegister)
        btnRegister.setOnClickListener {
            val intentregister = Intent(this, Register::class.java)
            startActivity(intentregister)
        }

        editTextUsername = findViewById(id.editTextUsername)
        editTextPassword = findViewById(id.editTextPassword)

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


        editTextUsername = findViewById(id.editTextUsername)
        editTextPassword = findViewById(id.editTextPassword)

        editTextUsername.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (editTextUsername.text.isEmpty()) {
                    showToast("Username Tidak Boleh Kosong! Mohon isi username terlebih dahulu.")
                    editTextUsername.requestFocus()
                } else {
                    showToast("Username Telah Diisi!")
                }
            }
        }

        editTextPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (editTextPassword.text.isEmpty()) {
                    showToast("Password Tidak Boleh Kosong! Mohon isi password terlebih dahulu.")
                    editTextPassword.requestFocus()
                } else {
                    showToast("Password Telah Diisi!")
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
