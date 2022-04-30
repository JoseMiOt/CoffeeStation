package com.coffeestation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityCafeteriaBoutiqueCafeBinding

class CafeteriaBoutiqueCafe : AppCompatActivity() {

    private lateinit var binding: ActivityCafeteriaBoutiqueCafeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCafeteriaBoutiqueCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContacto.setOnClickListener {
            hacerLlamada()
        }

        binding.btnUbicacion.setOnClickListener {
            val intent = Intent(this, UbicacionBoutiqueCafe::class.java)
            startActivity(intent)
        }

        binding.btnCafeCapuccino.setOnClickListener {
            val intent = Intent(this, InfoCafe::class.java)
            startActivity(intent)
        }

        binding.btnCafeLatte.setOnClickListener {
            val intent = Intent(this, CafeLatte::class.java)
            startActivity(intent)
        }

        binding.btnCafeLagrima.setOnClickListener {
            val intent = Intent(this, CafeLagrimaBC::class.java)
            startActivity(intent)
        }

        binding.btnCafeDoppio.setOnClickListener {
            val intent = Intent(this, CafeDoppio::class.java)
            startActivity(intent)
        }

        binding.btnCafeAffogato.setOnClickListener {
            val intent = Intent(this, CafeAffogato::class.java)
            startActivity(intent)
        }

        binding.btnCafeRistretto.setOnClickListener {
            val intent = Intent(this, CafeRistrettoBC::class.java)
            startActivity(intent)
        }

        binding.btnCafeColdBrew.setOnClickListener {
            val intent = Intent(this, CafeColdBrewBC::class.java)
            startActivity(intent)
        }

        binding.btnCafeEspresso.setOnClickListener {
            val intent = Intent(this, CafeExpresso::class.java)
            startActivity(intent)
        }

        binding.btnCafeVienes.setOnClickListener {
            val intent = Intent(this, CafeVienes::class.java)
            startActivity(intent)
        }
    }

    private fun hacerLlamada() {

        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$25524043")

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),105)

        } else {
            startActivity(intent)
        }
    }
}