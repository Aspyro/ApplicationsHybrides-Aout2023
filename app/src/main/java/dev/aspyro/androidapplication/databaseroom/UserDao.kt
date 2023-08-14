package dev.aspyro.androidapplication.databaseroom

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserTable")
    fun get(): List<UserRecord>

    @Query("SELECT * FROM UserTable WHERE (email = :email AND pwd = :password)")
    fun get(email: String, password: String): UserRecord

    @Query("SELECT COUNT() FROM UserTable WHERE (email = :email AND pwd = :password)")
    fun getCount(email: String, password: String): Int

    @Insert
    fun insertUser(vararg listCategories: UserRecord)

    @Update
    fun updateUser(task: UserRecord)

    @Delete
    fun deleteUser(task: UserRecord)
}