package com.example.dami.entity

sealed class Resultado<out T> {
    data class Exito<out T: Any>(val data: T): Resultado<T>()
    data class Problema(val error: Error): Resultado<Nothing>()
}