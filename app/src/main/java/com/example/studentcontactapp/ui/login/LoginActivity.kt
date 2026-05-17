package com.example.studentcontactapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcontactapp.R
import com.example.studentcontactapp.ui.main.MainActivity
import com.example.studentcontactapp.utils.PrefManager

class LoginActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefManager = PrefManager(this)

        // Auto login jika remember me aktif
        if (prefManager.isLogin() && prefManager.isRememberChecked()) {
            openMainPage()
            return
        }

        setContentView(R.layout.activity_login)

        val usernameField = findViewById<EditText>(R.id.etUsername)
        val passwordField = findViewById<EditText>(R.id.etPassword)
        val rememberCheck = findViewById<CheckBox>(R.id.cbRemember)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Isi username otomatis jika remember me aktif
        if (prefManager.isRememberChecked()) {
            usernameField.setText(prefManager.getUsername())
            rememberCheck.isChecked = true
        }

        loginButton.setOnClickListener {

            val username = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Username dan Password wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Login sederhana
            if (username == "bagas" && password == "12345") {

                prefManager.saveSession(
                    username,
                    rememberCheck.isChecked
                )

                Toast.makeText(
                    this,
                    "Login berhasil",
                    Toast.LENGTH_SHORT
                ).show()

                openMainPage()

            } else {

                Toast.makeText(
                    this,
                    "Login gagal",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openMainPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}