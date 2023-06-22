package com.example.dami.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dami.entity.Categoria
import com.example.dami.entity.Resultado
import com.example.dami.repository.CategoriaRepository
import kotlinx.coroutines.launch

class CategoriaViewModel(private val repository: CategoriaRepository): ViewModel() {

    private var _listaCategoria = MutableLiveData<Resultado<List<Categoria>>>()
    val listaCategoria: LiveData<Resultado<List<Categoria>>> = _listaCategoria

    fun listarCategorias(){
        viewModelScope.launch {
            val response = repository.listaCategoria()
            _listaCategoria.value = response
        }
    }
}