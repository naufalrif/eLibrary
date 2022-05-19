package com.example.elibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elibrary.R
import com.example.elibrary.adapter.BukuAdapter
import com.example.elibrary.manager.UserManager
import com.example.elibrary.viewmodel.ViewModelBuku
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var bukuadapter : BukuAdapter
    lateinit var viewmodelbuku : ViewModelBuku

    lateinit var userManager : UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userManager.userNAME.asLiveData().observe(this){
            tv_welcomeuser.text = "Welcome, $it"
        }

        getDataBuku()
        btn_profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    fun getDataBuku(){
        val viewmodel = ViewModelProvider(this).get(ViewModelBuku::class.java)
        viewmodel.getLiveBuku().observe(this, Observer {
            if (it != null){
                rv_film.layoutManager = LinearLayoutManager(this)
                bukuadapter = BukuAdapter {
                    val pindah = Intent(this, DetailActivity::class.java)
                    pindah.putExtra("detailbuku",it)
                    startActivity(pindah)
                }
                rv_film.adapter = bukuadapter
                bukuadapter.setDataBuku(it)
                bukuadapter.notifyDataSetChanged()
            }

        })
        viewmodel.makeAPIBuku()
    }
}