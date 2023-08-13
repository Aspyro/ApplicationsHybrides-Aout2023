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

class UserFormActivity : Activity() {

    private lateinit var emailEdit : EditText
    private lateinit var passwordEdit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        emailEdit = findViewById(R.id.et_email_inscription)
        passwordEdit = findViewById(R.id.et_password_inscription)
    }

    fun onFormClickManager(v : View) {
        when (v.id) {
            R.id.btn_FormValidation -> {
                writeUser()

                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun writeUser() {

        // Récupération des informations
        val userEmail = emailEdit.text.toString()
        val userPassword = emailEdit.text.toString()
        val userAccess = 1

        Log.i("User Creation Form", "Triying to create a User :\n$userEmail - $userPassword")

        // Création de l'utilisateur dans la base de données
        AsyncTask.execute {
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase.db")
                .build()

            val dao = db.userDao()
            try {
                val registeredUser = UserRecord(0, userEmail, userPassword, userAccess)
                dao.insertUser(registeredUser)
                Log.i("User Creation Form", "User created")
            }
            catch (e: Exception) {
                Log.i("User Creation Form", e.message.toString())
            }

        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("User Creation Form", "Méthode OnStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("User Creation Form", "Méthode onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.i("User Creation Form", "Méthode onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.i("User Creation Form", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("User Creation Form", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("User Creation Form", "Méthode OnStop")
    }
}