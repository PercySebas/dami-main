package com.example.dami.entity

class Categoria(val id_cat: Int,
                val nom_cat: String,
                val activo_Cat: Int?,
                val imagen_cat: String) {
    override fun toString(): String {
        return "Categoria(id_cat=$id_cat, nom_cat='$nom_cat', activo_Cat=$activo_Cat, imagen_cat='$imagen_cat')"
    }
}
/*
"id_cat": 4,
    "nom_cat": "Lavadora",
    "desc_cat": "Electrodoméstico destinado a limpiar el ropa mediante el uso de agua caliente o fría y detergentes",
    "activo_Cat": null,
    "imagen_cat": "1"
 */