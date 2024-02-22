package com.example.listacontactoskotlin.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Contact (
    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("direccion")
    @Expose
    val direccion: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("telefono")
    val telefono: Telefono
)
