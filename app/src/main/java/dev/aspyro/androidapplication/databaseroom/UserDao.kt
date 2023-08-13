package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserTable")
    fun get(): List<UserRecord>

    @Query("SELECT * FROM UserTable WHERE email = :email")
    fun get(email: String): UserRecord

    @Insert
    fun insertUser(vararg listCategories: UserRecord)

    @Update
    fun updateUser(task: UserRecord)

    @Delete
    fun deleteUser(task: UserRecord)
}