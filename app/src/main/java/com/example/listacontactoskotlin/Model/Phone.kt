package com.example.listacontactoskotlin.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Phone (
    @SerializedName("casa")
    val casa: String,

    @SerializedName("movil")
    val movil: String,

    @SerializedName("trabajo")
    val trabajo: String
)