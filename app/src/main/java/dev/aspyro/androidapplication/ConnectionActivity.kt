package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
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

                // TODO Vérifier les informations de l'utilisateur
                val connectingUser = User(0, email_edit.text.toString(), password_edit.text.toString(), 1)

                Log.i("User Connection", "Trying to fetch data for ${connectingUser.email.toString()}")
                AsyncTask.execute {
                    Looper.prepare()
                    val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase.db")
                        .build()
                    var connectedUser = UserRecord()
                    val dao = db.userDao()
                    try {
                        connectedUser = dao.get(connectingUser.email, connectingUser.pwd)
                    }
                    catch (e: Exception) {
                        Log.i("ERROR", e.message.toString())
                    }

                    if (connectedUser == null) {
                        Toast.makeText(this,
                            "L'utilisateur avec lequel vous souhaitez vous connecter n'existe pas. Veuillez réessayer.",
                            Toast.LENGTH_LONG)
                            .show()
                        intent = Intent(this, ConnectionActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        // Sauvegarder les informations de connexion
                        val sharedPreferences =
                            PreferenceManager.getDefaultSharedPreferences(applicationContext)
                        val editor = sharedPreferences.edit()

                        editor.putString("connectedUser", connectedUser.toString())
                        editor.apply()
                    }
                }

                // Lancer la vue adaptée selon les droits de l'utilisateur
                intent = Intent(this, ListingActivity::class.java)
                startActivity(intent)
            }
        }
    }
}