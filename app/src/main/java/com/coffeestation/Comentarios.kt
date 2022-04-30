package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityComentariosBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Comentarios : AppCompatActivity() {

    private lateinit var binding: ActivityComentariosBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityComentariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        user?.let {
            val correo = user.email.toString()

            db.collection("comments").document(correo).get().addOnSuccessListener {
                    binding.vTextoUsuario.setText(it.get(correo) as String?)
                    binding.vTextoComentario.setText(it.get("Comentarios") as String?)
                }
        }

        binding.btnActualizar.setOnClickListener{
            val user = Firebase.auth.currentUser
            user?.let {
                val correo = user.email.toString()

                db.collection("comments").document(correo).set(
                        hashMapOf("Comentarios" to binding.vTextoComentario.text.toString())
                    )
            }
            binding.vTextoComentario.setText("")
        }

        binding.btnrecupera.setOnClickListener{
            val user = Firebase.auth.currentUser
            user?.let {
                val correo = user.email.toString()
                db.collection("comments").document(correo).get().addOnSuccessListener {
                    binding.vTextoComentario.setText(it.get("Comentarios") as String?)
                }
            }
        }

        binding.btnEliminar.setOnClickListener{
            val user = Firebase.auth.currentUser
            user?.let {
                val correo = user.email.toString()
                db.collection("comments").document(correo).delete()
            }
            binding.vTextoComentario.setText("")
        }

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }

        binding.btnUser.setOnClickListener {
            val intent = Intent(this, InfoUsuario::class.java)
            startActivity(intent)
        }

        binding.btnFavorites.setOnClickListener {
            val intent = Intent(this, Favoritos::class.java)
            startActivity(intent)
        }

        binding.btnCarrito.setOnClickListener {
            val intent = Intent(this, CarritoDeCompras::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, General::class.java)
            startActivity(intent)
        }
    }
}