package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.coffeestation.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    //usuario = juanito12@gmail.com
    //contraseña = prueba1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
        }

        binding.btnHelp.setOnClickListener {
            val intent = Intent(this, ObtenAyuda::class.java)
            startActivity(intent)
        }

        binding.btnIniciarSesion.setOnClickListener {
            if (binding.eTextoCorreo.text.isNotEmpty() && binding.eTextoContrasena.text.isNotEmpty() ){
                haceLogin()
            }
            else{
                Toast.makeText(baseContext, getString(R.string.fallo_completar_espacios), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun haceLogin() {
        val correo = binding.eTextoCorreo.text.toString()
        val contrasena = binding.eTextoContrasena.text.toString()

        Log.d("correo", correo)
        Log.d("contrasena", contrasena)
        auth.signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this) { task->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    Toast.makeText(baseContext, getString(R.string.fallo_login), Toast.LENGTH_SHORT).show()
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

    public override fun onStart(){
        super.onStart()
        val user = auth.currentUser
        actualiza(user)
    }
}