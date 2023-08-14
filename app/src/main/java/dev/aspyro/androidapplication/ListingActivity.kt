package dev.aspyro.androidapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.room.Room
import dev.aspyro.androidapplication.databaseroom.AppDatabase
import dev.aspyro.androidapplication.databaseroom.AssetRecord
import dev.aspyro.androidapplication.databaseroom.UserRecord

class ListingActivity : Activity() {

    lateinit var tabdata: List<AssetRecord>
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        loadUI()
        loadData()


    }

    private fun loadUI() {
        // Afficher les boutons de gestion des Assets et des Utilisateurs si droit accès suffisants
    }

    private fun loadData() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            getString(R.string.DBName)
        )
            .allowMainThreadQueries().build()

        Log.i("Listing Activity", "Fetch Data")

        val dao = db.assetDao()
        try {
            tabdata = dao.get()
        } catch (e: Exception) {
            Log.i("Listing Activity", e.message.toString())
        }


        val tmptabdata = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        //var adapteur = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, tabdata)
        val adapteur = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, tmptabdata)

        val listView: ListView = findViewById(R.id.lv_listingProduit)
        listView.adapter = adapteur
        listView.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, id ->
            Toast.makeText(this@ListingActivity, position.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun onListingClickManager(view: View) {
        when(view.id) {
            R.id.btn_manageAssets -> {
                intent = Intent(this, ManageAssetsActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_manageUsers -> {
                intent = Intent(this, ManageUsersActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("ListingActivity", "Méthode OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ListingActivity", "Méthode onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ListingActivity", "Méthode onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ListingActivity", "Méthode onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ListingActivity", "Méthode OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ListingActivity", "Méthode OnStop")
    }
}