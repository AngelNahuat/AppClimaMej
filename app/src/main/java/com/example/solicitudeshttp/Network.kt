package com.example.solicitudeshttp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button

class Network {
    companion object{
      fun hayRed(activity: AppCompatActivity):Boolean{
          val connectivyMan=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
          val netInfo=connectivyMan.activeNetworkInfo
          return netInfo !=null && netInfo.isConnected
      }

    }
}