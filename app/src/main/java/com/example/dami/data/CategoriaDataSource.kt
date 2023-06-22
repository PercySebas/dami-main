package com.example.dami.data

import com.example.dami.entity.Categoria
import com.example.dami.entity.Resultado
import com.example.dami.network.ConexionApi
import com.example.dami.entity.Error
import java.net.SocketTimeoutException

class CategoriaDataSource {
    suspend fun servicioCategoria(): Resultado<List<Categoria>>{
        return try {
            val result = ConexionApi.retrofitService.obtenerCategoria()
            Resultado.Exito(result)
        } catch (ex: Exception){
            Resultado.Problema(Error("001","Error $ex"))
        } catch (ex: SocketTimeoutException){
            Resultado.Problema(Error("002","Error $ex"))
        }
    }
}