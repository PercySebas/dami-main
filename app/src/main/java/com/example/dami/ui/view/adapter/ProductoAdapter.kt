package com.example.dami.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dami.databinding.ItemProductoBinding
import com.example.dami.entity.Categoria
import com.example.dami.entity.Producto

class ProductoAdapter(val lista: List<Producto>, val onclick: (Producto) -> Unit): Adapter<ProductoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.bind(producto)
        holder.itemView.setOnClickListener {
            onclick(producto)
        }
    }
}