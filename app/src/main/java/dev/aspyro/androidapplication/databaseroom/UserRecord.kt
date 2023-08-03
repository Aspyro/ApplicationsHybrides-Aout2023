package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Entity(tableName = "UserTable")
data class UserRecord(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "pwd") var pwd: String,
    @ColumnInfo(name = "email") var email: String
)
