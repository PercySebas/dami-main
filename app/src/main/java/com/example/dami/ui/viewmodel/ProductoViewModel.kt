package com.example.dami.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dami.entity.Producto
import com.example.dami.entity.Resultado
import com.example.dami.entity.Error
import com.example.dami.repository.ProductoPorCateRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoPorCateRepository): ViewModel() {

    private var _listaProducto = MutableLiveData<Resultado<List<Producto>>>()
    val listaProducto: LiveData<Resultado<List<Producto>>> = _listaProducto
    //varibales para almacenar resultados de metodos post y delete
    private val _cambiarProductoResultado = MutableLiveData<Resultado<Int>>()
    val cambiarProductoResultado: LiveData<Resultado<Int>> get() = _cambiarProductoResultado

    fun listarProductos(id: Int){
        viewModelScope.launch {
            val response = repository.listaProductoPorCate(id)
            _listaProducto.value = response
        }
    }
    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                val resultado = repository.agregarProducto(producto)
                _cambiarProductoResultado.value = resultado
            } catch (ex: Exception) {
                _cambiarProductoResultado.value = Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }
    fun eliminarProducto(id: Int) {
        viewModelScope.launch {
            try {
                val resultado = repository.eliminarProducto(id) //Realizar la operacion
                _cambiarProductoResultado.value = resultado //Almacenar el resultado de la operacion
            } catch (ex: Exception) {
                _cambiarProductoResultado.value = Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }

}