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
import dev.aspyro.androidapplication.databaseroom.User
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
                if(writeAdmin()) {
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    private fun writeAdmin() : Boolean {

        // Récupération des informations
        val adminEmail = emailEdit.text.toString()
        val adminpassword = passwordEdit.text.toString()
        val adminAccess = 10

        if(!isEmail(adminEmail)) {
            emailEdit.error = "Entrez une adresse email valide."
            return false
        }
        else if (!isPasswordLongEnough(adminpassword)) {
            passwordEdit.error = "Votre mot de passe doit faire au moins 4 caractères."
            return false
        }
        else {
            val registeringAdmin = User(0, adminEmail, adminpassword, adminAccess)

            Log.i(
                "Admin Creation Form",
                "Trying to create an Admin :\n$adminEmail - $adminpassword"
            )

            // Création de l'administrateur dans la base de données

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                getString(R.string.DBName)
            )
                .allowMainThreadQueries().build()

            val registeredAdmin: UserRecord
            val dao = db.userDao()
            if(dao.getCount(registeringAdmin.email, registeringAdmin.pwd) == 0) {
                try {
                    registeredAdmin = UserRecord(0, adminEmail, adminpassword, adminAccess)
                    dao.insertUser(registeredAdmin)
                    Log.i("Admin Creation Form", "Admin created")
                } catch (e: Exception) {
                    Log.i("Admin Creation Form", e.message.toString())
                }
            } else {
                passwordEdit.error = "Votre utilisateur n'est pas valide. Veuillez entrer d'autres valeurs."
            }

        }
        return true
    }

    private fun isPasswordLongEnough(adminPassword: String) : Boolean {
        return adminPassword.length >= 4
    }

    private fun isEmail(adminEmail: String): Boolean {
        return (!TextUtils.isEmpty(adminEmail) && Patterns.EMAIL_ADDRESS.matcher(adminEmail).matches())
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