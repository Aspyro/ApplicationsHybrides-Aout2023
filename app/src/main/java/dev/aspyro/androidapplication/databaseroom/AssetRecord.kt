package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Entity(tableName="AssetTable")
data class AssetRecord(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "hardware") var hardware: String = "",
    @ColumnInfo(name = "brand") var brand: String = "",
    @ColumnInfo(name = "model") var model: String = "",
    @ColumnInfo(name = "reference") var reference: String = "",
    @ColumnInfo(name = "status") var status: String = ""
)