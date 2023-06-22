package com.example.dami.repository

import com.example.dami.data.ProductoPorCateDataSource
import com.example.dami.entity.Producto
import com.example.dami.entity.Resultado

class ProductoPorCateRepository(private val dataSource: ProductoPorCateDataSource) {
    suspend fun listaProductoPorCate(id: Int): Resultado<List<Producto>>{
        val response = dataSource.servicioProductoPorCate(id)
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
    suspend fun agregarProducto(producto: Producto): Resultado<Int> {
        val response = dataSource.agregarProducto(producto) //almacenando resultado
        return when (response) {
            is Resultado.Exito -> Resultado.Exito(1)
            is Resultado.Problema -> response
        }
    }
    //importante no olvidar pasar el atributo necesario segun el metodo a invocar
    suspend fun eliminarProducto(id: Int): Resultado<Int> {
        val response = dataSource.eliminarProducto(id)
        return when (response) {
            is Resultado.Exito -> Resultado.Exito(1)
            is Resultado.Problema -> response
        }
    }


}