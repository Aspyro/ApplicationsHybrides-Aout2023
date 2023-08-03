package dev.aspyro.androidapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class ListingActivity : Activity() {

    var tabdata : ArrayList<String> = ArrayList<String>()
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        tabdata.addAll(listOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat" ,"Lollipop" , "Marshmallow ", " Nougat ", "Oreo", "Pie"))

        var adapteur = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, tabdata)
        val listView : ListView = findViewById(R.id.lv_listingProduit)
        listView.adapter = adapteur

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