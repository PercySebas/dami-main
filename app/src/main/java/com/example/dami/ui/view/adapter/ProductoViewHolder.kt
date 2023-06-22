package com.example.dami.ui.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dami.databinding.ItemProductoBinding
import com.example.dami.entity.Producto

class ProductoViewHolder(private val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(producto: Producto){
        binding.lblNombreProd.text = producto.nom_prod
        binding.lblPreProd.text = "S/ ${producto.prec_prod.toString()}"
        val urlPhoto = producto.imagen_prod
        Glide.with(itemView.context)
            .load(urlPhoto)
            .centerCrop()
            .into(binding.imgProd)
    }
}