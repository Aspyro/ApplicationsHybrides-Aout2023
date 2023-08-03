package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Database(entities = [(UserRecord::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}