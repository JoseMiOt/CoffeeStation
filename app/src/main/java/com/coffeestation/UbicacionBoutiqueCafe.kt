package com.coffeestation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coffeestation.databinding.ActivityUbicacionBoutiqueCafeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UbicacionBoutiqueCafe : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityUbicacionBoutiqueCafeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUbicacionBoutiqueCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createFragment()
    }

    private fun createFragment() {
        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap){
        map = googleMap
        createMarker()
    }

    private fun createMarker(){
        val coordinates = LatLng(9.861446495747044, -83.91580828152047)
        val marker = MarkerOptions().position(coordinates).title("Cafetería")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null
        )
    }
}