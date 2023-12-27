package com.example.prueba_2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba_2.databinding.ActivityCambiarContra2Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CambiarContraActivity2 : AppCompatActivity() {


    private lateinit var binding: ActivityCambiarContra2Binding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarContra2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth

        binding.btnSalir.setOnClickListener {
            onBackPressed()
        }

        binding.btnCambiarClave.setOnClickListener {

            val user = Firebase.auth.currentUser!!

            val contraActual:String = binding.etContraActual.text.toString()
            val contraNueva:String = binding.etNewPassword.text.toString()
            val contraNueva2:String = binding.etNewPassword2.text.toString()


            val credential = EmailAuthProvider
                .getCredential(user!!.email.toString(), contraActual)

            user.reauthenticate(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,"Cambiando la contraseña , Espere por favor",Toast.LENGTH_LONG).show()
                        if(contraNueva==contraNueva2){
                            user!!.updatePassword(contraNueva)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this,"Datos Actualizados",Toast.LENGTH_LONG).show()
                                    }
                                }

                        }else{
                            Toast.makeText(this,"Contraseñas no coinciden",Toast.LENGTH_LONG).show()
                        }
                    }else {
                        Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
}
