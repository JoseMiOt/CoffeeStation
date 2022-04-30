package com.coffeestation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityFavoritosBinding

class Favoritos : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }

        binding.btnUser.setOnClickListener {
            val intent = Intent(this, InfoUsuario::class.java)
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

        binding.boutiqueCafe.setOnClickListener {
            val intent = Intent(this, CafeteriaBoutiqueCafe::class.java)
            startActivity(intent)
        }

        binding.cafeteriaBajoSombra.setOnClickListener {
            val intent = Intent(this, CafeteriaBajoSombra::class.java)
            startActivity(intent)
        }
    }
}