package com.example.listacontactoskotlin.Model

data class Contacto(
    val nombre: String,
    val direccion: String,
    val email: String,
    val telefono: Telefono
)
