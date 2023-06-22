package com.example.dami.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dami.data.CategoriaDataSource
import com.example.dami.data.ProductoPorCateDataSource
import com.example.dami.repository.CategoriaRepository
import com.example.dami.repository.ProductoPorCateRepository

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriaViewModel::class.java)) {
            return CategoriaViewModel(CategoriaRepository(CategoriaDataSource())) as T
        } else if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            return ProductoViewModel(ProductoPorCateRepository(ProductoPorCateDataSource())) as T
        }
        return super.create(modelClass)
    }
}