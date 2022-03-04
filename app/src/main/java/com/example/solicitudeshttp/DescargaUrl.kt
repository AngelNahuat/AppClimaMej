package com.example.solicitudeshttp

import android.os.AsyncTask
import android.os.StrictMode
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.jvm.Throws

class DescargaUrl(var completadoListener: CompletadoListener?):AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String? {
        try{
            return descargarDatos(params[0])
        }catch (e:IOException){
            return null
        }

fun onPostExecute(result: String){
    try{
        completadoListener?.descargaCompleta(result)

    }catch (e:Exception){

    }
}


    }
    @Throws(IOException::class)
    fun descargarDatos(url: String): String {
        val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var InputStream: InputStream? = null
        try {
            var url = URL(url)
            val conexion = url.openConnection() as HttpURLConnection
            conexion.requestMethod = "GET"
            conexion.connect()

            InputStream=conexion.inputStream
            return InputStream.bufferedReader().use { it.readText() }
        } finally {
            if (InputStream != null) {
                InputStream.close()
            }
        }
    }
}