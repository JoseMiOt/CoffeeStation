package com.coffeestation

import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.animation.Animator
import android.net.Uri
import com.airbnb.lottie.LottieAnimationView
import com.coffeestation.databinding.ActivityAcercaDeBinding

class AcercaDe : AppCompatActivity() {

    private lateinit var binding: ActivityAcercaDeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
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

        binding.vTextoCorreoSoporte.setOnClickListener {
            enviarCorreo()
        }

        binding.vTextoCorreoSoporte2.setOnClickListener {
            verSitioWeb()
        }

        var like = false
        var like2 = false
        var like3 = false

        binding.facebook.setOnClickListener{
            like = likeAnimationFacebook(binding.facebook, R.raw.facebookc,like)
            facebook()
        }

        binding.twitter.setOnClickListener{
            like2 = likeAnimationTwitter(binding.twitter, R.raw.twitter,like2)
            twitter()
        }

        binding.instagram.setOnClickListener{
            like3 = likeAnimationInstagram(binding.instagram, R.raw.igb,like3)
            instagram()
        }
    }

    private fun likeAnimationFacebook(imageView: LottieAnimationView, animation:Int, like: Boolean) : Boolean{
        if(!like){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate().alpha(0f).setDuration(200).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        imageView.setImageResource(R.drawable.facebook)
                        imageView.alpha = 1f
                    }
                })
            imageView.setImageResource(R.drawable.facebook)
        }
        return !like
    }

    private fun likeAnimationTwitter(imageView: LottieAnimationView, animation:Int, like2: Boolean) : Boolean{
        if(!like2){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate().alpha(0f).setDuration(200).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        imageView.setImageResource(R.drawable.twitter)
                        imageView.alpha = 1f
                    }
                })
            imageView.setImageResource(R.drawable.twitter)
        }
        return !like2
    }

    private fun likeAnimationInstagram(imageView: LottieAnimationView, animation:Int, like3: Boolean) : Boolean{
        if(!like3){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate().alpha(0f).setDuration(200).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        imageView.setImageResource(R.drawable.instagram)
                        imageView.alpha = 1f
                    }
                })
            imageView.setImageResource(R.drawable.instagram)
        }
        return !like3
    }

    private fun facebook() {
        val web = Uri.parse("https://www.facebook.com/profile.php?id=100081033870449")
        val intent = Intent(Intent.ACTION_VIEW, web)
        startActivity(intent)
    }

    private fun twitter() {
        val web = Uri.parse("https://twitter.com/coffeeStation__")
        val intent = Intent(Intent.ACTION_VIEW, web)
        startActivity(intent)
    }

    private fun instagram() {
        val web = Uri.parse("http://www.instagram.com/bajosombracafeteria/")
        val intent = Intent(Intent.ACTION_VIEW, web)
        startActivity(intent)
    }

    private fun verSitioWeb() {
        val web = Uri.parse("http://www.coffeestation.com")
        val intent = Intent(Intent.ACTION_VIEW, web)
        startActivity(intent)
    }

    private fun enviarCorreo() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL,arrayOf("support@coffeestation.com"))
        startActivity(intent)
    }
}