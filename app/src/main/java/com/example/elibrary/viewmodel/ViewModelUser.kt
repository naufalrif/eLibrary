package com.example.elibrary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.elibrary.room.user.User
import com.example.elibrary.room.user.UserDB
import com.example.elibrary.room.user.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelUser(application: Application) : AndroidViewModel(application) {
    lateinit var userrepo : UserRepo
    lateinit var cekdata : MutableLiveData<Int>
    init {
        val dao = UserDB.getInstance(application)?.userDao()
        userrepo = UserRepo(dao!!)
        cekdata = MutableLiveData()
    }

    fun registerLive(user : User) = viewModelScope.launch ( Dispatchers.IO ){
        userrepo.registerDao(user)
    }

    fun loginLive(user : String, password : String) = viewModelScope.launch(Dispatchers.IO) {
        cekdata.postValue(userrepo.loginCheckRepo(user,password))
    }
}