package com.example.dami.entity

import java.io.Serializable

class Producto(val id_prod: Int,
               val nom_prod: String,
               val id_cat: Int,
               val imagen_prod: String,
               val prec_prod: Double,
               val stock_prod: Int,
               val activo_prod: Int): Serializable {
    override fun toString(): String {
        return "Producto(id_prod=$id_prod, nom_prod='$nom_prod', id_cat=$id_cat, imagen_prod='$imagen_prod', prec_prod=$prec_prod, stock_prod=$stock_prod, activo_prod=$activo_prod)"
    }
}
/*
<activo_prod>1</activo_prod>
<id_cat>4</id_cat>
<id_prod>2</id_prod>
<imagen_prod>1</imagen_prod>
<nom_prod>Lavadora Digital Inverter 16kg</nom_prod>
<prec_prod>1399.00</prec_prod>
<stock_prod>20</stock_prod>
 */