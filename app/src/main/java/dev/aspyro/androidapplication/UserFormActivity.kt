package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import dev.aspyro.androidapplication.databaseroom.AppDatabase
import dev.aspyro.androidapplication.databaseroom.User
import dev.aspyro.androidapplication.databaseroom.UserRecord

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

                writeUser()

                intent = Intent(this, ListingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun writeUser() {

        val u = User(0, email_edit.text.toString(), password_edit.text.toString(), 1)
        Log.i("Database attempt", "Tried to create a user : ${u.toString()}")
        Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show()

        AsyncTask.execute {
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase.db")
                .build()

            val dao = db.userDao()
            try {
                val registeredUser = UserRecord(u.id, u.email, u.pwd, u.access)
                dao.insertUser(registeredUser)
                Log.i("test", "try catch dans async")
            }
            catch (e: Exception) {
                Log.i("ERROR", e.message.toString())
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