package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coffeestation.databinding.ActivityObtenAyudaBinding

class ObtenAyuda : AppCompatActivity() {

    private lateinit var binding: ActivityObtenAyudaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObtenAyudaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciarSesion.setOnClickListener {
            if (binding.eTextoCorreo.text.isNotEmpty()){
                val intent = Intent(this, AccederCuenta::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(baseContext, getString(R.string.fallo_completar_espacios), Toast.LENGTH_SHORT).show()
            }
        }
    }
}