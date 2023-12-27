package com.example.prueba_2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_2.Models.Producto
import com.example.prueba_2.R

class AdapterProducto(private val productos: ArrayList<Producto>,private val onEditarProductoClick: (Producto) -> Unit,private val onEliminarProductoClick: (Producto) -> Unit) :
    RecyclerView.Adapter<AdapterProducto.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val descripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val btnEliminar: View = itemView.findViewById(R.id.btnEliminaritem)
        val btnEditar: View = itemView.findViewById(R.id.btnEditaritem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]

        holder.nombre.text = producto.nombre
        holder.descripcion.text = producto.descripcion


        holder.btnEliminar.setOnClickListener {
            onEliminarProductoClick(producto)
        }

        holder.btnEditar.setOnClickListener {
            onEditarProductoClick(producto)
        }
    }
}