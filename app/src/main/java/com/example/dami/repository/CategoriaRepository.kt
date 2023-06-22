package com.example.dami.repository

import com.example.dami.data.CategoriaDataSource
import com.example.dami.entity.Categoria
import com.example.dami.entity.Resultado

class CategoriaRepository(private val dataSource: CategoriaDataSource) {
    suspend fun listaCategoria(): Resultado<List<Categoria>>{
        val response = dataSource.servicioCategoria()
        return when(response){
            is Resultado.Exito -> {
                val lista = response.data
                Resultado.Exito(lista)
            }
            is Resultado.Problema -> {
                response
            }
        }
    }
}