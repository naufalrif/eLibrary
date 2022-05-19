package com.example.elibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elibrary.room.peminjaman.Peminjaman
import com.example.elibrary.room.peminjaman.PeminjamanDB
import com.example.elibrary.room.peminjaman.PeminjamanRepo
import com.example.elibrary.room.user.User
import com.example.elibrary.room.user.UserDB
import com.example.elibrary.room.user.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelPinjam(application: Application) : AndroidViewModel(application) {
    lateinit var pinjamrepo : PeminjamanRepo
    lateinit var cekdata : MutableLiveData<List<Peminjaman>>
    init {
        val dao = PeminjamanDB.getInstance(application)?.peminjamanDao()
        pinjamrepo = PeminjamanRepo(dao!!)
        cekdata = MutableLiveData()
    }

    fun getLiveBukuObserver(): MutableLiveData<List<Peminjaman>> {
        return cekdata
    }

    fun pinjamLive(pinjam : Peminjaman) = viewModelScope.launch ( Dispatchers.IO ){
        pinjamrepo.pinjamDao(pinjam)
    }

    fun getPinjamLive(username : String) = viewModelScope.launch(Dispatchers.IO) {
        cekdata.postValue(pinjamrepo.getPinjamRepo(username))
    }
    fun kembaliLive(id : Int, username: String) = viewModelScope.launch(Dispatchers.IO) {
        pinjamrepo.kembaliRepo(id, username)
    }

//    fun loginLive(user : String, password : String) = viewModelScope.launch(Dispatchers.IO) {
//        cekdata.postValue(userrepo.loginCheckRepo(user,password))
//    }
}