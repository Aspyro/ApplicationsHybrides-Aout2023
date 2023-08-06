package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.EditText

class UserFormActivity : Activity() {

    lateinit var email_edit : EditText
    lateinit var password_edit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        email_edit = findViewById(R.id.et_email_inscription)
        password_edit = findViewById(R.id.et_password_inscription)
    }

    fun onFormClickManager(v : View) {
        when (v.id) {
            R.id.btn_FormValidation -> {

                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                val editor = sharedPreferences.edit()

                editor.putString("email_user", email_edit.text.toString())
                editor.apply()

                intent = Intent(this, ListingActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.i("FormActivity", "Méthode OnStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("FormActivity", "Méthode onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("FormActivity", "Méthode onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("FormActivity", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FormActivity", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FormActivity", "Méthode OnStop")
    }
}