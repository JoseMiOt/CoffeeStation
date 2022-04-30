package com.coffeestation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityAgradecimientoBinding

class Agradecimiento : AppCompatActivity() {

    private lateinit var binding: ActivityAgradecimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgradecimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}