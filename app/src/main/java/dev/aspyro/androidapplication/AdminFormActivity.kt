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

                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                val editor = sharedPreferences.edit()

                editor.putString("email_user", email_edit.text.toString())
                editor.apply()

                writeAdmin()

                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            //Toast.makeText(applicationContext,"Direction activité enfant", Toast.LENGTH_LONG).show()
        }

    }

    private fun writeAdmin() {

        val admin = User(0, email_edit.text.toString(), password_edit.text.toString(), 10)
        Log.i("Database attempt", "Trying to create an Admin :\n${admin.toString()}")
        Toast.makeText(this, admin.toString(), Toast.LENGTH_LONG).show()

        AsyncTask.execute{
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "MyDatabase.db")
                .build()

            val dao = db.userDao()
            try {
                val registeredAdmin = UserRecord(admin.id, admin.email, admin.pwd, admin.access)
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