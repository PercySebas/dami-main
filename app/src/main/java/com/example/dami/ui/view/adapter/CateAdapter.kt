package com.example.dami.ui.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dami.databinding.ItemCategoriaBinding
import com.example.dami.entity.Categoria

class CateAdapter(val lista : List<Categoria>, val onclick: (Categoria) -> Unit) : Adapter<CateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateViewHolder {
        val binding = ItemCategoriaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CateViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: CateViewHolder, position: Int) {
        val categoria = lista[position]
        holder.bind(categoria)
        holder.itemView.setOnClickListener {
            onclick(categoria)
        }
    }

}