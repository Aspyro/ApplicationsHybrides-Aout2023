package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class AdminFormActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_form)
    }

    fun onAdminFormClickManager(v : View) {
        /*when (v.id) {
            // Toast.makeText(applicationContext,"Direction activité enfant", Toast.LENGTH_LONG).show()
        }*/

    }

    override fun onStart() {
        super.onStart()
        Log.i("AdminFormActivity", "Méthode OnStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("AdminFormActivity", "Méthode onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("AdminFormActivity", "Méthode onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("AdminFormActivity", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("AdminFormActivity", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("AdminFormActivity", "Méthode OnStop")
    }
}