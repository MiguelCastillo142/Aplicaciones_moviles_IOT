package com.example.prueba_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_2.Adapter.AdapterProducto
import com.example.prueba_2.Models.Producto
import com.example.prueba_2.databinding.ActivityVerProductosBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerProductos : AppCompatActivity() {
    private lateinit var binding: ActivityVerProductosBinding
    private lateinit var productosList: ArrayList<Producto>
    private lateinit var productosReciclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var adapterProducto: AdapterProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productosReciclerView = binding.rvProductos
        productosReciclerView.layoutManager = LinearLayoutManager(this)
        productosReciclerView.setHasFixedSize(true)
        productosList = arrayListOf<Producto>()
        adapterProducto = AdapterProducto(
            productosList,
            ::onEditarProductoClick, // Modificado aquí
            ::onEliminarProductoClick
        )
        productosReciclerView.adapter = adapterProducto


        getProductos()
    }

    private fun getProductos() {
        database = FirebaseDatabase.getInstance().getReference("Productos")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    productosList.clear()
                    for (productosSnapshot in snapshot.children) {
                        val producto = productosSnapshot.getValue(Producto::class.java)
                        productosList.add(producto!!)
                    }
                    adapterProducto.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun onEliminarProductoClick(producto: Producto) {
        // Lógica para eliminar el producto
        database.child(producto.id.toString()).removeValue()
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
    }

    private fun onEditarProductoClick(producto: Producto) {
        // Lógica para abrir la pantalla de edición (usando Intent y Parcelable)
        val intent = Intent(this, EditarProductoActivity::class.java)
        intent.putExtra("PRODUCTO_KEY", producto)
        adapterProducto.notifyDataSetChanged()
        startActivity(intent)

    }

}
