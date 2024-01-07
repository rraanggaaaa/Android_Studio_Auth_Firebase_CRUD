package com.Ajol

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth


class Register : ComponentActivity() {


    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextRePassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnCommit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        StrictMode.setVmPolicy(
            VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build()
        )

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intentlogin = Intent(this, Login::class.java)
            startActivity(intentlogin)
        }


        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRePassword = findViewById(R.id.editTextRePassword)

        btnCommit = findViewById(R.id.btnCommit)
        // Set on Click Listener on Registration button
        btnCommit.setOnClickListener {

            // taking FirebaseAuth instance
            val auth: FirebaseAuth = FirebaseAuth.getInstance()

            val email = editTextEmail.text.toString()
            val pass = editTextPassword.text.toString()
            val retype = editTextRePassword.text.toString()

            editTextEmail.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
            editTextPassword.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
            editTextRePassword.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS

            editTextEmail.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)
            editTextPassword.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)
            editTextRePassword.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)
// check pass
            if (email.isBlank() || pass.isBlank() || retype.isBlank()) {
                Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            }

            if (pass != editTextRePassword.text.toString()) {
                Toast.makeText(
                    this,
                    "Password and Confirm Password do not match",
                    Toast.LENGTH_SHORT
                ).show()
            }
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Registration successful
                    val user = auth.currentUser
                    Toast.makeText(
                        this,
                        "Successfully Singed Up",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Singed Up Failed!",
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
}