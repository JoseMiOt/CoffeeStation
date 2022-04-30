package com.coffeestation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.coffeestation.databinding.ActivityInfoUsuarioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class InfoUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityInfoUsuarioBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = Firebase.auth.currentUser
        user?.let {
            val correo = user.email.toString()

            db.collection("users").document(correo)
                .get().addOnSuccessListener {
                    binding.vTextoUsuarioR.setText(it.get("Nombre Usuario") as String?)
                    binding.vTextoUsuarioR2.setText(it.get("Nombre Usuario") as String?)
                    binding.vTextoCorreoR.setText(it.get("Correo") as String?)
                    binding.vTextoNumeroR.setText(it.get("Telefono") as String?)
                    binding.vTextoFechaR.setText(it.get("Fecha Nacimiento") as String?)
                }
        }

        binding = ActivityInfoUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }

        binding.btnFavorites.setOnClickListener {
            val intent = Intent(this, Favoritos::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, General::class.java)
            startActivity(intent)
        }

        binding.btnCarrito.setOnClickListener {
            val intent = Intent(this, CarritoDeCompras::class.java)
            startActivity(intent)
        }

        binding.btnEditar.setOnClickListener {
            requestPermission()
        }
    }

    private fun requestPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGallery()
                }
                else -> requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else{
            pickPhotoFromGallery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            pickPhotoFromGallery()
        } else {
            Toast.makeText(this, R.string.fallo_permisos, Toast.LENGTH_SHORT).show()
        }
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data
            binding.fotoUsuario.setImageURI(data)
        }
    }

    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }
}