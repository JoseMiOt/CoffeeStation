package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.coffeestation.databinding.ActivityRegistroCuentaBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegistroCuenta : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistroCuentaBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding = ActivityRegistroCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            if (binding.eTextoCorreo.text.isNotEmpty() && binding.eTextoTelefono.text.isNotEmpty() && binding.eTextoFecha.text.isNotEmpty()){
                haceRegistro()
            }
            else {
                Toast.makeText(baseContext, getString(R.string.fallo_completar_espacios), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun haceRegistro(){
        val correo = binding.eTextoCorreo.text.toString()
        val contrasena = (getIntent().getStringExtra("CONTRASENA")).toString()
        val usuario = (getIntent().getStringExtra("USUARIO")).toString()

        auth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this) { task->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    db.collection("users").document(correo).set(
                        hashMapOf("Nombre Usuario" to usuario,"Correo" to correo,"Telefono" to binding.eTextoTelefono.text.toString(),
                            "Fecha Nacimiento" to binding.eTextoFecha.text.toString())
                    )
                    actualiza(user)
                } else {
                    Toast.makeText(baseContext, getString(R.string.fallo_registro), Toast.LENGTH_SHORT).show()
                    actualiza(null)
                }
            }
    }

    private fun actualiza(user: FirebaseUser?) {
        if (user != null){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }
}