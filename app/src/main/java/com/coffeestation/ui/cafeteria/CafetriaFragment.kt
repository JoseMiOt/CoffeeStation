package com.coffeestation.ui.cafeteria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coffeestation.R
import com.coffeestation.databinding.FragmentInicioBinding
import com.coffeestation.viewModel.CafeteriaViewModel

class CafetriaFragment : Fragment() {

    private lateinit var cafeteriaViewModel: CafeteriaViewModel
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cafeteriaViewModel =
            ViewModelProvider(this).get(CafeteriaViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        binding.btnUser.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cafeteria_to_nav_infoUsuario)
        }

        binding.btnFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cafeteria_to_nav_favoritos)
        }

        binding.btnCarrito.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cafeteria_to_nav_carritoDeCompras)
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cafeteria_to_nav_general)
        }

        binding.boutiqueCafe.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cafeteria_to_nav_cafeteriaBoutiqueCafe)
        }

        binding.cafeteriaBajoSombra.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cafeteria_to_nav_cafeteriaBajoSombra)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}