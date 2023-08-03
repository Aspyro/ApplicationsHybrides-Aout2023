package dev.aspyro.androidapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "Méthode OnStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "Méthode onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "Méthode onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "Méthode OnStop")
    }
}