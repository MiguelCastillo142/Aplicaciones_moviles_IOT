package com.example.prueba_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prueba_2.databinding.ActivityPostLoginBinding
import android.content.Context
import android.content.SharedPreferences

class PostLogin : AppCompatActivity() {

    // Nombre de las preferencias compartidas
    private val PREFS_NAME = "MyPrefsFile"

    // Clave para el indicador de primera vez
    private val FIRST_TIME_KEY = "firstTime"

    // Agregar viewbinding
    private lateinit var binding: ActivityPostLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verificar si es la primera vez
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstTime = prefs.getBoolean(FIRST_TIME_KEY, true)

        if (isFirstTime) {
            // Mostrar el mensaje de bienvenida (puedes cambiar el mensaje según tus necesidades)
            Toast.makeText(this, "¡Bienvenido a fireAndroid!", Toast.LENGTH_LONG).show()

            // Actualizar el indicador de primera vez
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putBoolean(FIRST_TIME_KEY, false)
            editor.apply()
        }

        // Muestra el fragment de home al iniciar la actividad
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, com.example.prueba_2.HomeFragment())
            .commit()

        // Inicializar bottomNav
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    // Muestra el fragment de home
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, com.example.prueba_2.HomeFragment()).commit()
                    true
                }
                R.id.nav_settings -> {
                    // Muestra el fragment de settings
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, com.example.prueba_2.SettingsFragment()).commit()
                    true
                }
                R.id.nav_agregar -> {
                    // Muestra el fragment de agregar
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, com.example.prueba_2.AgregarFragment()).commit()
                    true
                }
                else -> false
            }
        }

        // Controla las ocasiones en que el usuario presiona nuevamente el bottomnavigation
        binding.bottomNav.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Ya estás en la vista home", Toast.LENGTH_LONG).show()
                R.id.nav_settings -> Toast.makeText(this, "Ya estás en la vista datos", Toast.LENGTH_LONG).show()
            }
        }
    }
}
