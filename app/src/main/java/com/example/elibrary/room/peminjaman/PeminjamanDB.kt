package com.example.elibrary.room.peminjaman

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elibrary.room.user.User
import com.example.elibrary.room.user.UserDB
import com.example.elibrary.room.user.UserDao

@Database(entities = [Peminjaman::class], version = 1)
abstract class PeminjamanDB : RoomDatabase() {

    abstract fun peminjamanDao(): PeminjamanDao

    companion object {
        private var INSTANCE: PeminjamanDB? = null
        fun getInstance(context: Context): PeminjamanDB? {
            if (INSTANCE == null) {
                synchronized(Peminjaman::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PeminjamanDB::class.java, "Peminjaman2.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}