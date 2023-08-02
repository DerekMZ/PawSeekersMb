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


private lateinit var name: TextView
private lateinit var correo: TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    name = findViewById(R.id.testi)
    correo = findViewById(R.id.testu)

        val testing = "000001"
        consulta(testing)

        //views
        var btnReg = findViewById(R.id.btnReg) as Button
        var btnIn = findViewById(R.id.btnIn) as Button
        var btnMap = findViewById(R.id.btnMap) as Button

        btnReg.setOnClickListener{
            val goReg = Intent(this, Register::class.java) //start register
            startActivity(goReg)
        }

        btnIn.setOnClickListener{
            val goLog = Intent(this, Login::class.java) //Start Login
            startActivity(goLog)
        }

        btnMap.setOnClickListener{
           val goMap = Intent( this, MapsActivity::class.java) //Start Maps
        startActivity(goMap)
        }
    }



    private lateinit var requestQueue: RequestQueue


    private fun consulta(testing: String){


        requestQueue = Volley.newRequestQueue(this)


    var url = "https://api-bd-connection.vercel.app$testing"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                Log.d("RESPUESTA",response.toString())
                val corX = response.getString("x")
                val corY = response.getString("y")

                name.text = "X: $corX"
                correo.text = "Y: $corY"

            },
            { error ->
                // Manejar el error
                Log.e(ContentValues.TAG, "Error en la solicitud: $error")
                name.text = "ERROR EN LA CONSULTA"
                correo.text = "ERROR EN LA CONSULTA"
            })

        requestQueue.add(request)
    }
}