package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Dao
interface AssetDao {

    @Query("SELECT * FROM AssetTable")
    fun get(): List<AssetRecord>

    @Query("SELECT * FROM AssetTable WHERE (id = :id AND reference = :reference)")
    fun get(id: Int, reference: String): AssetRecord

    @Query("SELECT COUNT() FROM AssetTable WHERE (id = :id and reference = :reference)")
    fun getCount(id: Int, reference: String) : Int

    @Insert
    fun insertAsset(vararg listCategories: AssetRecord)

    @Update
    fun updateAsset(task: AssetRecord)

    @Delete
    fun deleteAsset(task: AssetRecord)
}