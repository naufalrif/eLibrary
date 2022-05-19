package com.example.elibrary.room.user

class UserRepo(private val dao : UserDao) {
    suspend fun registerDao(user : User){
        dao.registerUser(user)
    }

    suspend fun loginCheckRepo(user : String, password : String) : Int{
        return dao.cekLogin(user,password)

    }
}