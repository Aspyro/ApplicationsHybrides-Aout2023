package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Database(entities = arrayOf(UserRecord::class, AssetRecord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun assetDao(): AssetDao
}