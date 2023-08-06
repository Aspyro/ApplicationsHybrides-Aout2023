package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSharedPreferences(getString(R.string.app_shared_prefs), Context.MODE_PRIVATE)?.let { sharedPreferences ->
            val isFirstTimeOpening = sharedPreferences.getBoolean(getString(R.string.first_time_opening), true)

            if(isFirstTimeOpening) {
                showAdminForm()
                with(sharedPreferences.edit()) {
                    putBoolean(getString(R.string.first_time_opening), false)
                    apply()
                }
            }
            else showUserForm()
        }
    }

    private fun showUserForm() {
        intent = Intent(this, UserFormActivity::class.java)
        startActivity(intent)
    }

    private fun showAdminForm() {
        intent = Intent(this, AdminFormActivity::class.java)
        startActivity(intent)
    }

    fun onMainClickManager(v : View) {
        when (v.id) {
            R.id.btn_mainToChild -> {
                Toast.makeText(applicationContext,"Direction activité enfant", Toast.LENGTH_LONG).show()
                intent = Intent(this, AdminFormActivity::class.java)
                startActivity(intent)
            }
        }
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