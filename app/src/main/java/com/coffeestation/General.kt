package com.coffeestation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityGeneralBinding

class General : AppCompatActivity() {

    private lateinit var binding: ActivityGeneralBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGeneralBinding.inflate(layoutInflater)
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

        binding.btnArrow.setOnClickListener {
            val intent = Intent(this, Herramientas::class.java)
            startActivity(intent)
        }

        binding.btnArrow2.setOnClickListener {
            val intent = Intent(this, CalificarApp::class.java)
            startActivity(intent)
        }

        binding.btnArrow3.setOnClickListener {
            val intent = Intent(this, Descripcion::class.java)
            startActivity(intent)
        }

        binding.btnArrow4.setOnClickListener {
            Whatsapp()
        }

        binding.btnArrow5.setOnClickListener {
            val intent = Intent(this, AcercaDe::class.java)
            startActivity(intent)
        }
    }

    private fun Whatsapp() {

        val intent = Intent(Intent.ACTION_VIEW)
        val uri = "whatsapp://send?phone=506$85642226&text="

        intent.setPackage("com.whatsapp")
        intent.data = Uri.parse(uri)
        startActivity(intent)
    }
}