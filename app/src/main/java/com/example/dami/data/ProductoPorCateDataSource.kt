package com.example.dami.data

import com.example.dami.entity.Resultado
import com.example.dami.network.ConexionApi
import com.example.dami.entity.Error
import com.example.dami.entity.Producto
import java.net.SocketTimeoutException

class ProductoPorCateDataSource {
    suspend fun servicioProductoPorCate(id: Int): Resultado<List<Producto>>{
        return try {
            val result = ConexionApi.retrofitService.obtenerProductosPorCategoria(id) //pasar el valor del id
            Resultado.Exito(result)
        } catch (ex: Exception){
            Resultado.Problema(Error("001","Error $ex"))
        } catch (ex: SocketTimeoutException){
            Resultado.Problema(Error("002","Error $ex"))
        }
    }
    suspend fun agregarProducto(producto: Producto): Resultado<Int> {
        return try {
            //almacenando en una constante el resultado
            val response = ConexionApi.retrofitService.agregarProducto(producto)
            if (response > 0) {
                Resultado.Exito(response)
            } else {
                Resultado.Problema(Error("003", "Error en la solicitud"))
            }
        } catch (ex: Exception) {
            Resultado.Problema(Error("001", "Error $ex"))
        } catch (ex: SocketTimeoutException) {
            Resultado.Problema(Error("002", "Error $ex"))
        }
    }
    suspend fun eliminarProducto(id: Int): Resultado<Int> {
        return try {
            val response = ConexionApi.retrofitService.eliminarProducto(id)
            if (response > 0) {
                Resultado.Exito(response)
            } else {
                Resultado.Problema(Error("003", "Error en la solicitud"))
            }
        } catch (ex: Exception) {
            Resultado.Problema(Error("001", "Error $ex"))
        } catch (ex: SocketTimeoutException) {
            Resultado.Problema(Error("002", "Error $ex"))
        }
    }


}