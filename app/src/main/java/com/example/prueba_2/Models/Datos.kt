package com.example.prueba_2.Models

import android.util.Log

// Se tiene la clase datos, la cual hay una variable de temperatura en modo string
data class Datos(val temperatura: String = "") {
    companion object {
        // Se revisa que el modelo Datos sea un string y no este nulo
        fun fromFirebaseModel(firebaseDatos: Map<String, Any>): Datos {
            //la variable temperatura, se busca en firebase para como un string
            val temperatura = firebaseDatos["Temperatura"]?.toString() ?: ""
            // despues de leer la temperatura, se muestra el mensaje
            Log.d("Datos", "Temperatura mapeada desde Firebase: $temperatura")
            // se devuelve los Datos que temperatura tendran los mismos valores
            return Datos(temperatura = temperatura)
        }
    }
}



