package com.coffeestation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityCafeteriaBajoSombraBinding

class CafeteriaBajoSombra : AppCompatActivity() {

    private lateinit var binding: ActivityCafeteriaBajoSombraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCafeteriaBajoSombraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContacto.setOnClickListener{
            hacerLlamada()
        }

        binding.btnUbicacion.setOnClickListener{
            val intent = Intent(this, UbicacionBajoSombra::class.java)
            startActivity(intent)
        }

        binding.ivFrappuccino.setOnClickListener{
            val intent = Intent(this, CafeFrappuccino::class.java)
            startActivity(intent)
        }

        binding.ivColdbrew.setOnClickListener{
            val intent = Intent(this, CafeColdBrew::class.java)
            startActivity(intent)
        }

        binding.ivMoca.setOnClickListener{
            val intent = Intent(this, CafeMoca::class.java)
            startActivity(intent)
        }

        binding.ivFrio.setOnClickListener{
            val intent = Intent(this, CafeFrio::class.java)
            startActivity(intent)
        }

        binding.ivCn.setOnClickListener{
            val intent = Intent(this, CafeNegro::class.java)
            startActivity(intent)
        }

        binding.ivRistretto.setOnClickListener{
            val intent = Intent(this, CafeRistretto::class.java)
            startActivity(intent)
        }

        binding.ivLagrima.setOnClickListener{
            val intent = Intent(this, CafeLagrima::class.java)
            startActivity(intent)
        }
    }

    private fun hacerLlamada() {

        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$85642226")

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),105)

        } else {
            startActivity(intent)
        }
    }
}