package com.coffeestation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityAccederCuentaBinding

class AccederCuenta : AppCompatActivity() {

    private lateinit var binding: ActivityAccederCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccederCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpcion1.setOnClickListener {
            enviarCorreo()
        }

        binding.btnOpcion2.setOnClickListener {
            enviarMensaje()
        }
    }

    private fun enviarMensaje() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("smsto:+" + 50612345678)

        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(arrayOf(Manifest.permission.SEND_SMS),105)

        } else {
            startActivity(intent)
        }
    }

    private fun enviarCorreo() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL,arrayOf("support@coffeestation.com"))
        startActivity(intent)
    }
}