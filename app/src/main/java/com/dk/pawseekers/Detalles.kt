package com.dk.pawseekers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Detalles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val url = "https://paw-seekers-api-vented5.vercel.app/api/missing_nearby/0"
        val textoResultado = findViewById<TextView>(R.id.textApi)
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,url, Response.Listener { pets ->
            textoResultado.text = "La respuesta es: ${pets}"
        }, Response.ErrorListener { textoResultado.text = "Algo Salio mal" })

        queue.add(stringRequest)
    }
}