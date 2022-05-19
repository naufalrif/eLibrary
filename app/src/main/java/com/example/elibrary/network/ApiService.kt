package com.example.elibrary.network

import com.example.elibrary.model.GetAllBukuItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("buku")
    fun getAllBuku() : Call<List<GetAllBukuItem>>
}