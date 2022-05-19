package com.example.elibrary.room.peminjaman

class PeminjamanRepo(private val dao : PeminjamanDao) {
    suspend fun pinjamDao(peminjam : Peminjaman){
        dao.insertPinjam(peminjam)
    }

    suspend fun getPinjamRepo(username : String) : List<Peminjaman>{
        return dao.getPinjaman(username)
    }

    suspend fun kembaliRepo(id : Int, username: String) {
        dao.kembali(id, username)
    }
}