package com.example.elibrary.room.peminjaman

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Entity
@Parcelize
data class Peminjaman (
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo(name = "username")
    var username : String,
    @ColumnInfo(name = "id_buku")
    var idBuku : Int,
    @ColumnInfo(name = "deadline")
    var deadline : String?,
    @ColumnInfo(name =  "judul")
    val judul: String,
    @ColumnInfo(name ="penerbit")
    val penerbit: String,
    @ColumnInfo(name = "penulis")
    val penulis: String,
    @ColumnInfo(name =  "sampul")
    val sampul: String,
    @ColumnInfo(name = "sinopsis")
    val sinopsis: String,
    @ColumnInfo(name = "tanggalRilis")
    val tanggalRilis: String
) : Parcelable