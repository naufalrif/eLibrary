package com.example.elibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.example.elibrary.R
import com.example.elibrary.model.GetAllBukuItem
import com.example.elibrary.room.peminjaman.Peminjaman
import com.example.elibrary.room.peminjaman.PeminjamanDB
import com.example.elibrary.room.user.User
import com.example.elibrary.viewmodel.ViewModelPinjam
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    var pinjamdb : PeminjamanDB? = null
    lateinit var viewmodel : ViewModelPinjam
    lateinit var userManager : com.example.elibrary.manager.UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        userManager = com.example.elibrary.manager.UserManager(this)
        pinjamdb = PeminjamanDB.getInstance(this)
        viewmodel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelPinjam::class.java)
        val detailbuku = intent.getParcelableExtra<GetAllBukuItem>("detailbuku")

        val judul = detailbuku?.judul
        val penerbit = detailbuku?.penerbit
        val penulis = detailbuku?.penulis
        val sinopsis = detailbuku?.sinopsis
        val tanggalRilis = detailbuku?.tanggalRilis
        val sampul = detailbuku?.sampul

        tv_detail_title.text = judul
//        txtPenerbit.text = penerbit
//        txtPenulis.text = penulis
        tv_detail_desc.text = sinopsis
//        txtTanggal.text = tanggalRilis

        Glide.with(applicationContext).load(detailbuku?.sampul).into(img_detail_buku)

        btn_pinjam.setOnClickListener {
            userManager.userNAME.asLiveData().observe(this){
                var username = it.toString()
                if(tv_langganan.text.equals("Premium")){
                    GlobalScope.launch {
                        viewmodel.pinjamLive(Peminjaman(null,username,detailbuku?.id!!.toInt(),"PREMIUM",judul!!, penerbit!!, penulis!!, sampul!! , sinopsis!!, tanggalRilis!!))
                        runOnUiThread {
                            Toast.makeText(this@DetailActivity,"Berhasil meminjam premium!",Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    GlobalScope.launch {
                        viewmodel.pinjamLive(Peminjaman(null, username, detailbuku?.id!!.toInt(), detailbuku?.tanggalPinjam.toString(),  judul!!, penerbit!!, penulis!!, sampul!! , sinopsis!!, tanggalRilis!!))
                        runOnUiThread {
                            Toast.makeText(this@DetailActivity,"Berhasil!",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}