package com.dk.pawseekers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to all views
        var et_user_name = findViewById(R.id.user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_submit = findViewById(R.id.btn_submit) as Button
        var btnReturn = findViewById(R.id.btnReturn) as Button



        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text;
            Toast.makeText(this@Login, user_name, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }

        btnReturn.setOnClickListener{
            val goHome = Intent(this, MainActivity::class.java)
            startActivity(goHome)
        }
    }
}