package com.example.listacontactoskotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listacontactoskotlin.Adapter.ContactosAdapter
import com.example.listacontactoskotlin.Model.Contacto
import com.example.listacontactoskotlin.Model.Persona
import com.example.listacontactoskotlin.Util.Analisis.analizarContactos
import com.example.listacontactoskotlin.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: ContactosAdapter

    companion object {
        const val CONTACTOS = "contactos.json"
        const val CONTACTS = "contacts.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view: View = binding.root
        setContentView(view)
        binding.button.setOnClickListener(this)

        crearAdapter()
        // onClick(binding.button);
    }

    private fun crearAdapter() {
        // Create adapter passing in the sample user data
        adapter = ContactosAdapter(obtenerContactos())
        // Attach the adapter to the recyclerview to populate items
        binding.recyclerView.adapter = adapter
        // Set layout manager to position the items
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View) {
        var contactos: ArrayList<Contacto> = obtenerContactos()

        if (v === binding.button) {
            if (contactos.size > 0)
            // adapter.notifyDataSetChanged();
            // adapter.notifyItemRangeChanged(0, contactos.size());
                adapter.actualizar(contactos)
            else
                mostrarMensaje("Error al obtener los contactos")
        }
    }

    fun obtenerContactos(): ArrayList<Contacto> {
        var contactos: ArrayList<Contacto> = ArrayList()
        lateinit var contenido: String
        lateinit var gson: Gson
        lateinit var persona: Persona

        try {
            if (!binding.switch1.isChecked) {
                // Analizar contactos
                contenido = leerAsset(applicationContext, CONTACTOS)
                contactos = analizarContactos(contenido)
            } else {
                // usar Gson
                contenido = leerAsset(applicationContext, CONTACTS)
                gson = Gson()
                persona = gson.fromJson(contenido, Persona::class.java)
                contactos = persona.contactos as ArrayList<Contacto>
            }
        } catch (e: IOException) {
            Log.e("Error: ", e.message.toString())
            mostrarMensaje("Error: " + e.message.toString())
        } catch (e: JsonSyntaxException) {
            Log.e("Error: ", e.message.toString())
            mostrarMensaje("Error: " + e.message.toString())
        } catch (e: JSONException) {
            //e.printStackTrace();
            Log.e("Error: ", e.message.toString())
            mostrarMensaje("Error: " + e.message.toString())
        }

        return contactos
    }

    @Throws(IOException::class)
    fun leerAsset(context: Context, fileName: String): String {
        val jsonString: String

        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

        return jsonString
    }

    private fun mostrarMensaje(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }
}