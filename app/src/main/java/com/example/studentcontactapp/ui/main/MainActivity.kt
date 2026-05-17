package com.example.studentcontactapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentcontactapp.R
import com.example.studentcontactapp.ui.login.LoginActivity
import com.example.studentcontactapp.utils.PrefManager

class MainActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        prefManager = PrefManager(this)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val logoutButton = findViewById<Button>(R.id.btnLogout)

        welcomeText.text =
            "Selamat Datang, ${prefManager.getUsername()}"

        logoutButton.setOnClickListener {

            prefManager.logout()

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}