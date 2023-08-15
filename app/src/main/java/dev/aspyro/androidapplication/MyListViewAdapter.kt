package dev.aspyro.androidapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dev.aspyro.androidapplication.databaseroom.Asset

class MyListViewAdapter(private val context: Context, private val arrayList: ArrayList<Asset>) : BaseAdapter() {

    private lateinit var assetId: TextView
    private lateinit var assetHardware: TextView
    private lateinit var assetBrand: TextView
    private lateinit var assetModel: TextView
    private lateinit var assetReference: TextView
    private lateinit var assetStatus: TextView

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.listingrow, parent, false)
        assetId = convertView.findViewById(R.id.assetId)
        assetHardware = convertView.findViewById(R.id.assetHardware)
        assetBrand = convertView.findViewById(R.id.assetBrand)
        assetModel = convertView.findViewById(R.id.assetModel)
        assetReference = convertView.findViewById(R.id.assetReference)
        assetStatus = convertView.findViewById(R.id.assetStatus)

        assetId.text = arrayList[position].id.toString()
        assetHardware.text = arrayList[position].hardware.toString()
        assetBrand.text = arrayList[position].brand.toString()
        assetModel.text = arrayList[position].model.toString()
        assetReference.text = arrayList[position].reference.toString()
        assetStatus.text = arrayList[position].status.toString()

        return convertView
    }
}