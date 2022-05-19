package com.example.elibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.elibrary.model.GetAllBukuItem
import com.example.elibrary.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelBuku : ViewModel() {
    lateinit var liveDataBuku : MutableLiveData<List<GetAllBukuItem>>

    init {
        liveDataBuku = MutableLiveData()
    }

    fun getLiveBuku() : MutableLiveData<List<GetAllBukuItem>>{
        return liveDataBuku
    }

    fun makeAPIBuku(){
        ApiClient.instance.getAllBuku()
            .enqueue(object : Callback<List<GetAllBukuItem>> {
                override fun onResponse(
                    call: Call<List<GetAllBukuItem>>,
                    response: Response<List<GetAllBukuItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataBuku.postValue(response.body())
                    }else{

                    }
                }

                override fun onFailure(call: Call<List<GetAllBukuItem>>, t: Throwable) {

                }

            })
    }
}