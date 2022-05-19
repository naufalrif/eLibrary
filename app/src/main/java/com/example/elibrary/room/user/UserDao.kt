package com.example.elibrary.room.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun registerUser(user: User) : Long

    @Query("SELECT EXISTS(SELECT * FROM User WHERE username = :userName AND password = :pass)")
    fun cekLogin(userName : String, pass : String) : Int
}