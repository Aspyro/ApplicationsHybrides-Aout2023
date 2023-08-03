package dev.aspyro.androidapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ListingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ListingActivity", "Méthode OnStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("ListingActivity", "Méthode onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("ListingActivity", "Méthode onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("ListingActivity", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ListingActivity", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ListingActivity", "Méthode OnStop")
    }
}