package com.example.prueba_2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prueba_2.Models.Producto
import com.example.prueba_2.databinding.FragmentAgregarBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AgregarFragment : Fragment() {

    private lateinit var binding: FragmentAgregarBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgregarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().getReference("Productos")

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombreProducto.text.toString()
            val descripcion = binding.etDescripcionProducto.text.toString()
            val id = database.child("Productos").push().key

            if (nombre.isEmpty()) {
                binding.etNombreProducto.error = "Ingrese un nombre"
                return@setOnClickListener
            }
            if (descripcion.isEmpty()) {
                binding.etDescripcionProducto.error = "Ingrese una descripcion"
                return@setOnClickListener
            }
            val producto = Producto(id, nombre, descripcion)
            database.child(id!!).setValue(producto)
                .addOnSuccessListener {
                    Snackbar.make(binding.root, "Producto ingresado", Snackbar.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    // Manejar el caso en que la escritura en la base de datos falla
                    Snackbar.make(binding.root, "Error al ingresar el producto", Snackbar.LENGTH_LONG).show()
                }
        }

        binding.btnVer.setOnClickListener {
            val intent = Intent(requireContext(), VerProductos::class.java)
            startActivity(intent)
        }

    }
}


