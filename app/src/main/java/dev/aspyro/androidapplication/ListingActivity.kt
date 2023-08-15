package dev.aspyro.androidapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.room.Room
import dev.aspyro.androidapplication.databaseroom.AppDatabase
import dev.aspyro.androidapplication.databaseroom.Asset
import dev.aspyro.androidapplication.databaseroom.AssetRecord

class ListingActivity : Activity() {

    lateinit var tabdata: List<AssetRecord>
        private set

    lateinit var editTextLayout : LinearLayout
    lateinit var manageUsersLayout : LinearLayout
    lateinit var editTextId : EditText
    lateinit var editTextReference : EditText
    lateinit var editTextStatus : EditText
    lateinit var editTextHardware : EditText
    lateinit var editTextBrand : EditText
    lateinit var editTextModel : EditText
    lateinit var btnAddAsset : Button
    var modifiedItemId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        editTextLayout = findViewById(R.id.linear_et_layout)
        editTextId = findViewById(R.id.et_assetId)
        editTextReference = findViewById(R.id.et_assetReference)
        editTextStatus = findViewById(R.id.et_assetStatus)
        editTextHardware = findViewById(R.id.et_assetHardware)
        editTextBrand = findViewById(R.id.et_assetBrand)
        editTextModel = findViewById(R.id.et_assetModel)

        manageUsersLayout = findViewById(R.id.ll_manageusers)
        btnAddAsset = findViewById(R.id.btn_addAsset)

        loadUI()
        loadData()


    }

    private fun loadUI() {
        // Afficher les boutons de gestion des Assets et des Utilisateurs si droit accès suffisants
        val sharedPreferences = getSharedPreferences(getString(R.string.app_shared_prefs), MODE_PRIVATE)

        val accessValue = sharedPreferences.getString("connectedUserAccess", "1")?.toInt()

        if(accessValue == 1) {
            btnAddAsset.isEnabled = false
            btnAddAsset.visibility = View.INVISIBLE
            manageUsersLayout.visibility = View.GONE
        }
    }

    private fun loadData() {
        val db = getDb()

        Log.i("Listing Activity", "Fetch Data")

        val dao = db.assetDao()
        try {
            tabdata = dao.get()
        } catch (e: Exception) {
            Log.i("Listing Activity", e.message.toString())
        }

        var dataArray : ArrayList<Asset> = ArrayList()
        for(data in tabdata) {
            dataArray.add(Asset(data.id, data.hardware, data.brand, data.model, data.reference, data.status))
        }

        val adapteur = MyListViewAdapter(this, dataArray)

        val listView: ListView = findViewById(R.id.lv_listingProduit)
        listView.adapter = adapteur

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if(editTextLayout.visibility == View.VISIBLE) {
                editTextId.setText(dataArray[position].id.toString())
                editTextReference.setText(dataArray[position].reference.toString())
                editTextStatus.setText(dataArray[position].status.toString())
                editTextHardware.setText(dataArray[position].hardware.toString())
                editTextBrand.setText(dataArray[position].brand.toString())
                editTextModel.setText(dataArray[position].model.toString())
                modifiedItemId = dataArray[position].id
            }
        }
    }

    fun onListingClickManager(view: View) {
        when(view.id) {
            R.id.btn_manageAssets -> {
                when(editTextLayout.visibility) {
                    View.VISIBLE -> {
                        val isAssetUpdated = updateAsset(getAsset(editTextId.text.toString().toInt()))

                        if(isAssetUpdated) {
                            editTextLayout.visibility = View.GONE
                            Toast.makeText(applicationContext, "Apareil mis à jour", Toast.LENGTH_LONG).show()
                            loadData()
                        }
                        else {
                            Toast.makeText(applicationContext, "L'appareil n'a pas pu être mis à jour. Veuillez réessayer.",
                                Toast.LENGTH_LONG).show()
                        }
                    }
                    View.GONE -> {
                        resetEditTexts()
                    }

                    View.INVISIBLE -> { }
                }
            }
            R.id.btn_manageUsers -> {

            }
            R.id.btn_addAsset -> {
                when(editTextLayout.visibility) {
                    View.VISIBLE -> {
                        val isAssetAdded = addAsset(getAsset())
                        if (isAssetAdded) {
                            editTextLayout.visibility = View.GONE
                            Toast.makeText(applicationContext, "Apareil ajouté", Toast.LENGTH_LONG).show()
                            loadData()
                        }
                        else {
                            Toast.makeText(applicationContext, "L'appareil n'a pas pu être ajouté. Veuillez réessayer",
                                Toast.LENGTH_LONG).show()
                        }
                    }
                    View.GONE -> {
                        resetEditTexts()
                    }
                    View.INVISIBLE -> { }
                }


            }
        }
    }

    private fun resetEditTexts() {
        editTextId.text = null
        editTextReference.text = null
        editTextStatus.text = null
        editTextHardware.text = null
        editTextBrand.text = null
        editTextModel.text = null
        editTextLayout.visibility = View.VISIBLE
    }

    private fun addAsset(asset: AssetRecord): Boolean {
        Log.i("ListingActivity", "Add new asset")
        val db = getDb()

        val dao = db.assetDao()

        try {
            dao.insertAsset(asset)
        } catch (e: Exception){
            Log.i("Listing Activity", e.message.toString())
            return false
        }
        return true
    }

    private fun updateAsset(asset : AssetRecord) : Boolean {
        Log.i("ListingActivity", "Update asset")
        val db = getDb()

        val dao = db.assetDao()
        try {
            dao.updateAsset(asset)
        } catch (e: Exception) {
            Log.i("Listing Activity", e.message.toString())
            return false
        }

        return true
    }

    private fun getDb() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        getString(R.string.DBName)
    ).allowMainThreadQueries().build()

    private fun getAsset(assetId : Int = 0): AssetRecord {
        Log.i("ListingActivity", "Get asset")
        return AssetRecord(assetId,
            editTextHardware.text.toString(),
            editTextBrand.text.toString(),
            editTextModel.text.toString(),
            editTextReference.text.toString(),
            editTextStatus.text.toString())
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