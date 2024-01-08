package com.Ajol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Profile : ComponentActivity() {
    lateinit var btnBack : ImageView
    private lateinit var editTextEmailUpdate: EditText
    private lateinit var editTextPasswordUpdate: EditText
    private lateinit var editTextNewPasswordUpdate: EditText
    private lateinit var btnUpdate: Button

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initializeViews()

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener{
            val intentback = Intent(this, MainActivity::class.java)
            startActivity(intentback)
        }

        btnUpdate.setOnClickListener {
            updatePassword()
        }

        btnDelete.setOnClickListener {
            deleteUserAccount()
        }
    }

    private fun initializeViews() {
        editTextEmailUpdate = findViewById(R.id.editTextEmailUpdate)
        editTextPasswordUpdate = findViewById(R.id.editTextPasswordUpdate)
        editTextNewPasswordUpdate = findViewById(R.id.editTextNewPasswordUpdate)
        btnUpdate = findViewById(R.id.btnUpdate)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun updatePassword() {
        val credential: AuthCredential = EmailAuthProvider.getCredential(
            editTextEmailUpdate.text.toString(),
            editTextPasswordUpdate.text.toString()
        )

        editTextEmailUpdate.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        editTextPasswordUpdate.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        editTextNewPasswordUpdate.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS

        editTextEmailUpdate.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)
        editTextPasswordUpdate.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)
        editTextNewPasswordUpdate.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            user.reauthenticate(credential).addOnCompleteListener { reauthTask ->
                if (reauthTask.isSuccessful) {
                    // Change email address
                    user.updatePassword(editTextNewPasswordUpdate.text.toString())
                        .addOnCompleteListener { updatePasswordTask ->
                        if (updatePasswordTask.isSuccessful) {
                            navigateToLogin()
                            Toast.makeText(this, "Password Changed, Please Re-Login", Toast.LENGTH_LONG).show()
                        }else {
                            Toast.makeText(this, "Change Password failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this, "You are not signed in.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteUserAccount() {
        val credential: AuthCredential = EmailAuthProvider.getCredential(
            editTextEmail.text.toString(),
            editTextPassword.text.toString()
        )

        editTextEmail.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        editTextPassword.importantForAutofill = EditText.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS

        editTextEmail.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)
        editTextPassword.setAutofillHints(View.AUTOFILL_HINT_PASSWORD)

        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        user?.reauthenticate(credential)?.addOnCompleteListener { reauthTask ->
            if (reauthTask.isSuccessful) {
                user.delete()
                    .addOnCompleteListener { deleteTask ->
                        if (deleteTask.isSuccessful) {
                            Log.d("TAG", "User account deleted.")
                            navigateToLogin()
                            Toast.makeText(this, "Deleted User Successfully", Toast.LENGTH_LONG).show()
                        } else {
                            Log.w("TAG", "Failed to delete user", deleteTask.exception)
                            Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Log.w("TAG", "Reauthentication failed", reauthTask.exception)
                Toast.makeText(this, "Reauthentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@Profile, Login::class.java)
        startActivity(intent)
    }
}
