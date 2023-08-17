package com.dk.pawseekers

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //views
        var btnReg = findViewById(R.id.btnReg) as Button
        var btnIn = findViewById(R.id.btnIn) as Button
        var btnInfo = findViewById(R.id.btnInfo) as Button
        var btnMap = findViewById(R.id.btnMap) as Button        //variables de los botones

        btnReg.setOnClickListener{
            val goReg = Intent(this, Register::class.java) //start register
            startActivity(goReg)
        }

        btnIn.setOnClickListener{
            val goLog = Intent(this, Login::class.java) //Start Login
            startActivity(goLog)
        }
        btnInfo.setOnClickListener{
            val goDetail = Intent(this, Detalles::class.java) //Start Login
            startActivity(goDetail)
        }

        btnMap.setOnClickListener{
           val goMap = Intent( this, MapsActivity::class.java) //Start Maps
        startActivity(goMap)
        }
    }







}