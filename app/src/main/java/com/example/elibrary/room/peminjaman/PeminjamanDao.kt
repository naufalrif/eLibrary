package com.example.elibrary.room.peminjaman

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeminjamanDao {
    @Insert
    fun insertPinjam(peminjam : Peminjaman) : Long

    @Query("SELECT * FROM Peminjaman WHERE username = :username")
    fun getPinjaman(username : String) : List<Peminjaman>

    @Query("DELETE FROM Peminjaman WHERE id_buku = :id AND username = :username ")
    suspend fun kembali(id: Int, username: String)

}