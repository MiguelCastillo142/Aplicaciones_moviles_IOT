package com.example.prueba_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prueba_2.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var signInAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inicializar la instancia de FirebaseAuth
        auth = Firebase.auth
        // Configuración del botón de inicio de sesión
        binding.btnLogin.setOnClickListener {
            signInAttempts++
            // Verificar si ha habido más de un intento de inicio de sesión
            if (signInAttempts > 1) {
                Toast.makeText(this, "Ya se ha intentado iniciar sesión. Por favor, espera.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Obtener el correo electrónico y la contraseña
            val email = binding.etusuario.text.toString()
            val password = binding.etpassword.text.toString()
            // Validar que se haya ingresado un correo electrónico
            if (email.isEmpty()) {
                binding.etusuario.error = "Por favor ingrese un usuario"
                signInAttempts = 0
                return@setOnClickListener
            }
            // Validar que se haya ingresado una contraseña
            if (password.isEmpty()) {
                binding.etpassword.error = "Por favor ingrese una contraseña"
                signInAttempts = 0
                return@setOnClickListener
            }
            // Llamar a la función signIn para intentar iniciar sesión
            signIn(email, password)
        }
    }

    // Función para hacer la autenticación
    private fun signIn(email: String, password: String) {
        try {
            // Intentar iniciar sesión con el correo electrónico y la contraseña
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    signInAttempts = 0
                    // Verificar si la tarea se completó exitosamente
                    if (task.isSuccessful) {
                        // Muestra un mensaje de éxito y y no redirige al PostLogin
                        Toast.makeText(this, "Inicio correcto", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, PostLogin::class.java)
                        startActivity(intent)
                    } else {
                        // Muestra un mensaje de error si el inicio de sesion es incorrecto
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            // Manejar excepciones, reiniciar el contador de intentos y mostrar un mensaje de error
            signInAttempts = 0
            Toast.makeText(this, "Error al intentar iniciar sesión", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}



