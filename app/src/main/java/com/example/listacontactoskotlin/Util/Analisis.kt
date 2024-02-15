package com.example.listacontactoskotlin.Util

import com.example.listacontactoskotlin.Model.Contacto
import com.example.listacontactoskotlin.Model.Telefono
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object Analisis {

        @Throws(JSONException::class)
        fun analizarContactos(cadena: String?): ArrayList<Contacto> {
            val jAcontactos: JSONArray
            val jOobjeto: JSONObject
            var jOcontacto: JSONObject
            var jOtelefono: JSONObject
            var contacto: Contacto
            var telefono: Telefono
            val personas = ArrayList<Contacto>()

            // añadir contactos (en formato JSON) a personas
            jOobjeto = JSONObject(cadena)
            //jAcontactos = JSONArray(jOobjeto.getString("contactos"))
            jAcontactos = jOobjeto.getJSONArray("contactos")
            for (i in 0 until jAcontactos.length()) {
                jOcontacto = jAcontactos.getJSONObject(i)
                jOtelefono = jOcontacto.getJSONObject("telefono")
                telefono = Telefono (jOtelefono.getString("casa"), jOtelefono.getString("movil"), jOtelefono.getString("trabajo"))
                contacto = Contacto(jOcontacto.getString("nombre"), jOcontacto.getString("direccion"), jOcontacto.getString("email"), telefono)
                personas.add(contacto)
            }

            return personas
        }
}