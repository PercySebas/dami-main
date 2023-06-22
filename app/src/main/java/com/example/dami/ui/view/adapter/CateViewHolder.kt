package com.example.dami.ui.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dami.databinding.ItemCategoriaBinding
import com.example.dami.entity.Categoria

class CateViewHolder(private val binding: ItemCategoriaBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(categoria: Categoria){
        binding.lblNombreCategoria.text = categoria.nom_cat
        val urlPhoto = categoria.imagen_cat
        Glide.with(itemView.context)
            .load(urlPhoto)
            .centerCrop()
            .into(binding.imgCategoria)

    }
}