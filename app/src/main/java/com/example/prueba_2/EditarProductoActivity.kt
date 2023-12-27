package com.example.prueba_2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba_2.Models.Producto
import com.example.prueba_2.databinding.ActivityEditarProductoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditarProductoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarProductoBinding
    private lateinit var database: DatabaseReference
    private var producto: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtén la referencia de la base de datos
        database = FirebaseDatabase.getInstance().getReference("Productos")

        // Obtén el producto de la intención
        producto = intent.getParcelableExtra("PRODUCTO_KEY")

        // Verifica si el producto es nulo
        if (producto == null) {
            Toast.makeText(this, "Error: Producto no encontrado", Toast.LENGTH_SHORT).show()
            finish()  // Cierra la actividad si el producto es nulo
            return
        }

        // Llena los campos de edición con los datos actuales del producto
        binding.etNombreProducto.setText(producto?.nombre)
        binding.etDescripcionProducto.setText(producto?.descripcion)

        // Añade la lógica para la edición del producto
        binding.btnGuardarCambios.setOnClickListener {
            // Obtiene los nuevos datos del producto
            val nombreActualizado = binding.etNombreProducto.text.toString()
            val descripcionActualizada = binding.etDescripcionProducto.text.toString()

            if (nombreActualizado.isEmpty() || descripcionActualizada.isEmpty()) {
                // Muestra un mensaje de error si los campos están vacíos
                // Puedes personalizar esto según tus necesidades
                Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Actualiza los datos en la base de datos
            val productoActualizado = Producto(producto!!.id, nombreActualizado, descripcionActualizada)
            database.child(producto!!.id!!).setValue(productoActualizado)
                .addOnSuccessListener {
                    // Maneja el caso en que la actualización es exitosa
                    Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
                    finish()  // Cierra la actividad después de la actualización
                }
                .addOnFailureListener {
                    // Maneja el caso en que la actualización falla
                    Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
