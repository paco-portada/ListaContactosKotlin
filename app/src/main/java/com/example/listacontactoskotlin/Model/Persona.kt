package com.example.listacontactoskotlin.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Persona (
    @SerializedName("contactos")
    val contactos: List<Contacto>
)
