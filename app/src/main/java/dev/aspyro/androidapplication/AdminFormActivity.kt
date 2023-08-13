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

    private lateinit var emailEdit : EditText
    private lateinit var passwordEdit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_form)

        emailEdit = findViewById(R.id.et_email_inscriptionAdmin)
        passwordEdit = findViewById(R.id.et_password_inscriptionAdmin)
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
        val adminEmail = emailEdit.text.toString()
        val adminpassword = passwordEdit.text.toString()

        Log.i("Admin Creation Form", "Trying to create an Admin :\n$adminEmail - $adminpassword")

        // Création de l'administrateur dans la base de données
        AsyncTask.execute{
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase.db")
                .build()

            val dao = db.userDao()
            try {
                val registeredAdmin = UserRecord(0, adminEmail, adminpassword, 10)
                dao.insertUser(registeredAdmin)
                Log.i("Admin Creation Form", "Admin created")
            }
            catch (e: Exception) {
                Log.i("Admin Creation Form", e.message.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Admin Creation Form", "Méthode OnStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("Admin Creation Form", "Méthode onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("Admin Creation Form", "Méthode onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("Admin Creation Form", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Admin Creation Form", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Admin Creation Form", "Méthode OnStop")
    }
}