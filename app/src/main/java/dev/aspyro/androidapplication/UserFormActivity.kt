package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
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
                if(writeUser()) {
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun writeUser() : Boolean {

        // Récupération des informations
        val userEmail = emailEdit.text.toString()
        val userPassword = passwordEdit.text.toString()
        val userAccess = 1

        if(!isEmail(userEmail)) {
            emailEdit.error = "Entrez une adresse email valide."
            return false
        }
        else if (!isPasswordLongEnough(userPassword)) {
            passwordEdit.error = "Votre mot de passe doit faire au moins 4 caractères."
            return false
        }
        else {
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
        return true
    }

    private fun isPasswordLongEnough(userPassword: String): Boolean {
        return userPassword.length >= 4
    }

    private fun isEmail(userEmail: String): Boolean {
        return (!TextUtils.isEmpty(userEmail) && Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
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