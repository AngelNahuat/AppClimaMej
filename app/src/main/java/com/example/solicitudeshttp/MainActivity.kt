package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity(), CompletadoListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bValidar = findViewById<Button>(R.id.bValidar)
        val bSolicitud = findViewById<Button>(R.id.bSolicitud)
        val bVolley = findViewById<Button>(R.id.bVolley)
        val bOk = findViewById<Button>(R.id.bOK)
        bValidar.setOnClickListener {
            if (Network.hayRed(this)) {
                Toast.makeText(this, "Esta conectado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No estas conectado", Toast.LENGTH_LONG).show()
            }
        }
        bSolicitud.setOnClickListener {
            if (Network.hayRed(this)) {
                // Log.d("bSolicitud.onCLick",descargarDatos("https://www.google.com"))
                DescargaUrl(this).execute("https://www.google.com")
            } else {
                Toast.makeText(this, "No estas conectado", Toast.LENGTH_LONG).show()
            }
        }
        bVolley.setOnClickListener {
            if (Network.hayRed(this)) {
                SolVolley("https://google.com")
            } else {
                Toast.makeText(this, "No estas conectado", Toast.LENGTH_LONG).show()
            }
        }

        bOk.setOnClickListener {
            if (Network.hayRed(this)) {
                okHTTP("https://google.com")
            } else {
                Toast.makeText(this, "No estas conectado", Toast.LENGTH_LONG).show()
            }
        }


    }

    fun okHTTP(url: String) {
        val cliente = OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()
        cliente.newCall(solicitud).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: okhttp3.Response) {
                val resultado=response.body().toString()
                this@MainActivity.runOnUiThread{
                    try{
                        Log.d("SolOK",resultado)
                    }catch (e:Exception){

                    }
                }
            }
        })
    }

    fun SolVolley(url: String) {
        val cola = Volley.newRequestQueue(this)
        val solicitud =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {
                    Log.d("solHTTP", response)
                } catch (e: Exception) {

                }
            }, Response.ErrorListener { })
        cola.add(solicitud)
    }

    override fun descargaCompleta(resultado: String) {
        Log.d("descargacom", resultado)
    }

}