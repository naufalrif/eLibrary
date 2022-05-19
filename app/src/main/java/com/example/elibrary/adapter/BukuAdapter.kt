package com.example.elibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elibrary.R
import com.example.elibrary.model.GetAllBukuItem
import kotlinx.android.synthetic.main.item_adapter_buku_gratis.view.*

class BukuAdapter(private var onClick : (GetAllBukuItem)->Unit) : RecyclerView.Adapter<BukuAdapter.ViewHolder>() {
    private var databuku : List<GetAllBukuItem>? = null

    fun setDataBuku(buku : List<GetAllBukuItem>){
        this.databuku = buku
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter_buku_gratis, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_judulfilm.text = databuku!![position].judul
        holder.itemView.tv_penulis.text = databuku!![position].penulis
        holder.itemView.tv_penerbit.text = databuku!![position].penerbit
        Glide.with(holder.itemView.context).load(databuku!![position].sampul).into(holder.itemView.img_buku)
        holder.itemView.cardbuku.setOnClickListener {
            onClick(databuku!![position])
        }
    }

    override fun getItemCount(): Int {
        if (databuku == null){
            return 0
        }
        else{
            return databuku?.size!!
        }
    }
}