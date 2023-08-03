package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AdminFormActivity : Activity() {

    lateinit var email_edit : EditText
    lateinit var password_edit : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_form)

        email_edit = findViewById(R.id.et_email_inscriptionAdmin)
        password_edit = findViewById(R.id.et_password_inscriptionAdmin)
    }

    fun onAdminFormClickManager(v : View) {
        when (v.id) {
            R.id.btn_adminFormValidation -> {
                Toast.makeText(applicationContext,
                    "Email : " + email_edit.text + "\nMot de passe : " + password_edit.text,
                    Toast.LENGTH_LONG)
                .show()
                intent = Intent(this, ListingActivity::class.java)
                startActivity(intent)
            }
            //Toast.makeText(applicationContext,"Direction activité enfant", Toast.LENGTH_LONG).show()
        }

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