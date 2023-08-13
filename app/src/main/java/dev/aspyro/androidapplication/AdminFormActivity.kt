package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.room.Room
import dev.aspyro.androidapplication.databaseroom.AppDatabase
import dev.aspyro.androidapplication.databaseroom.UserRecord

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
                writeAdmin()

                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun writeAdmin() {

        // Récupération des informations
        val adminEmail = email_edit.text.toString()
        val adminpassword = password_edit.text.toString()

        Log.i("Database attempt", "Trying to create an Admin :\n$adminEmail - $adminpassword")

        // Création de l'administrateur dans la base de données
        AsyncTask.execute{
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase.db")
                .build()

            val dao = db.userDao()
            try {
                val registeredAdmin = UserRecord(0, adminEmail, adminpassword, 10)
                dao.insertUser(registeredAdmin)
                Log.i("Database attempt", "Admin created")
            }
            catch (e: Exception) {
                Log.i("ERROR", e.message.toString())
            }
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