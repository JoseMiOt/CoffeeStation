package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityDescripcionBinding

class Descripcion : AppCompatActivity() {

    private lateinit var binding: ActivityDescripcionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDescripcionBinding.inflate(layoutInflater)
        setContentView(binding.root)

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