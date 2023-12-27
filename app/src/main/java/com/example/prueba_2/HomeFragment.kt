package com.example.prueba_2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.prueba_2.Models.Datos
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aca se obtiene la base de datos especificamente en datos/Temp1
        databaseReference = FirebaseDatabase.getInstance().reference.child("datos/Temp1")

        // Aca obtiene los datos desde el realtime de firebase
        obtenerDatosDesdeFirebase()
    }


    private fun actualizarInterfazUsuario(datos: Datos) {
        val rvDatos = view?.findViewById<TextView>(R.id.rvDatos)
        rvDatos?.text = datos.temperatura
    }


    private fun obtenerDatosDesdeFirebase() {
        // Aca me dice ver los cambios, si hay un cambio se actualiza
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Se intenta deserializar para poder obtener los datos en formato string, ademas revisa si el campo no esta en blanco
                val genericTypeIndicator = object : GenericTypeIndicator<Map<String, Any>>() {}
                val firebaseDatos = snapshot.getValue(genericTypeIndicator)

                // el log sirve para mostrar los datos desde firebase
                Log.d("Datos", "Datos desde Firebase: $firebaseDatos")

                // se revisa si el datos no es nulo
                if (firebaseDatos != null) {
                    val datos = Datos.fromFirebaseModel(firebaseDatos)
                    // se actualiza el interfaz de usuario si los datos temperatura no son nulos
                    if (datos.temperatura.isNotBlank()) {
                        actualizarInterfazUsuario(datos)
                    } else {
                        // Muestra el mensaje si los datos estan nulos o estan estan en blanco
                        Log.e("Datos", "El campo 'temperatura' en Datos es nulo o está en blanco.")
                    }

                } else {
                    // Muestra el mensaje que los datos obtenidos son totalmente nulos
                    Log.e("Datos", "Datos obtenidos desde Firebase son nulos.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Aqui se manejan los errores
                Log.e("Datos", "Error en la obtención de datos desde Firebase: ${error.message}")
            }
        })
    }
}
