package dev.aspyro.androidapplication

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

class UserFormActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        Toast.makeText(applicationContext,
            "oui", Toast.LENGTH_LONG)
            .show()
    }
}