package com.piyawat.android_coinranking

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.piyawat.android_coinranking.ui.CoinsListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isNetworkConnected()){
            startActivity(Intent(this,CoinsListActivity::class.java))
            finish()
        }
    }


    private fun isNetworkConnected() : Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getActiveNetworkInfo() != null && cm.activeNetworkInfo.isConnected
    }
}