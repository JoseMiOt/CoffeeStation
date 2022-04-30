package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coffeestation.databinding.ActivityCarritoDeComprasBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CarritoDeCompras : AppCompatActivity() {

    private lateinit var binding: ActivityCarritoDeComprasBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarritoDeComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnComprar.setOnClickListener {
            if (binding.terminosDeServicio.isChecked){
                if (binding.btnPaypal.isChecked || binding.btnTarjetaDePago.isChecked || binding.btnTarjetaDeRegalo.isChecked){
                    val intent = Intent(this, Agradecimiento::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, getString(R.string.fallo_completar_espacios), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(baseContext, getString(R.string.fallo_completar_espacios), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnOk.setOnClickListener {
            if (binding.eTextoComentarios.text.isNotEmpty()){
                val user = Firebase.auth.currentUser
                user?.let {
                    val correo = user.email.toString()

                    db.collection("comments").document(correo).set(
                            hashMapOf("Comentarios" to binding.eTextoComentarios.text.toString())
                        )
                }
                binding.eTextoComentarios.setText("")
            } else {
                Toast.makeText(baseContext, getString(R.string.fallo_mensaje), Toast.LENGTH_SHORT).show()
            }
        }
    }
}