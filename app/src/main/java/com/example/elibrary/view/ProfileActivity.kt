package com.example.elibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elibrary.R
import com.example.elibrary.adapter.PinjamAdapter
import com.example.elibrary.manager.UserManager
import com.example.elibrary.model.GetAllBukuItem
import com.example.elibrary.room.peminjaman.PeminjamanDB
import com.example.elibrary.viewmodel.ViewModelPinjam
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    var pinjamandb : PeminjamanDB? = null
    lateinit var viewmodel : ViewModelPinjam
    lateinit var userManager: UserManager
    lateinit var pinjamAdapter : PinjamAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        pinjamandb = PeminjamanDB.getInstance(this)
        userManager = UserManager(this)

        var username = ""
        userManager.userNAME.asLiveData().observe(this){
            username = it
            getAllPinjaman(username)
        }

        btn_logout.setOnClickListener {
            GlobalScope.launch {
                userManager.logout()
                userManager.setStatus("no")
            }
            startActivity(Intent(this, SplashActivity::class.java))
        }
    }

    fun getAllPinjaman(username : String){
        viewmodel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelPinjam::class.java)


        viewmodel.getLiveBukuObserver().observe(this@ProfileActivity){
            if (it != null){
                rv_film.layoutManager = LinearLayoutManager(this@ProfileActivity)
                pinjamAdapter = PinjamAdapter (){
                    val pindah = Intent(this@ProfileActivity, DetailActivity::class.java)
                    val detailBuku : GetAllBukuItem = GetAllBukuItem("asdasd", it.idBuku.toString(),it.judul, it.penerbit,it.penulis,it.sampul,
                        it.sinopsis, 0, it.tanggalRilis)
                    pindah.putExtra("detailbuku", detailBuku)
                    startActivity(pindah)
                }
                rv_film.adapter = pinjamAdapter
                pinjamAdapter.setDataBuku(it)
                pinjamAdapter.notifyDataSetChanged()
            }else{

            }

        }
        viewmodel.getPinjamLive(username)


    }
}