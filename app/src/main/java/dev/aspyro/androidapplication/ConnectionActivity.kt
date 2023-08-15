package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.room.Room
import dev.aspyro.androidapplication.databaseroom.AppDatabase
import dev.aspyro.androidapplication.databaseroom.User
import dev.aspyro.androidapplication.databaseroom.UserRecord

class ConnectionActivity : Activity() {

    lateinit var email_edit : EditText
    lateinit var password_edit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)

        email_edit = findViewById(R.id.et_email_connection)
        password_edit = findViewById(R.id.et_password_connection)
    }

    fun onFormClickManager(v: View) {
        when(v.id) {
            R.id.btn_FormValidation -> {
                val user_email = email_edit.text.toString()
                val user_password = password_edit.text.toString()

                if(!isEmail(user_email)) {
                    email_edit.error = "Entrez une adresse email valide."
                }
                else if(!isPasswordLongEnough(user_password)) {
                    password_edit.error = "Votre mot de passe doit faire au moins 4 caractères."
                }
                else {
                    val connectingUser =
                        User(0, user_email, user_password, 1)

                    Log.i(
                        "User Connection",
                        "Trying to fetch data for ${connectingUser.email.toString()}"
                    )

                    val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        getString(R.string.DBName)
                    )
                        .allowMainThreadQueries().build()
                    var connectedUser: UserRecord
                    val dao = db.userDao()

                    if (dao.getCount(connectingUser.email, connectingUser.pwd) > 0) {
                        try {
                            connectedUser = dao.get(connectingUser.email, connectingUser.pwd)

                            val sharedPreferences =
                                PreferenceManager.getDefaultSharedPreferences(applicationContext)
                            val editor = sharedPreferences.edit()

                            editor.putString("connectedUserId", connectedUser.id.toString())
                            editor.putString("connectedUserEmail", connectedUser.email.toString())
                            editor.putString("connectedUserAccess", connectedUser.access.toString())
                            editor.apply()

                            // Lancer la vue adaptée selon les droits de l'utilisateur
                            intent = Intent(this, ListingActivity::class.java)
                            startActivity(intent)
                        } catch (e: Exception) {
                            Log.i("ERROR", e.message.toString())
                        }
                    } else {
                        password_edit.setError("L'utilisateur avec lequel vous souhaitez vous connecter n'existe pas. Veuillez réessayer.")
                    }

                }
            }
        }
    }

    private fun isPasswordLongEnough(adminPassword: String) : Boolean {
        return adminPassword.length >= 4
    }

    private fun isEmail(adminEmail: String): Boolean {
        return (!TextUtils.isEmpty(adminEmail) && Patterns.EMAIL_ADDRESS.matcher(adminEmail).matches())
    }
}