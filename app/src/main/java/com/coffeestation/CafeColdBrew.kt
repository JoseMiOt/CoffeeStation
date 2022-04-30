package com.coffeestation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.coffeestation.databinding.ActivityCafeColdBrewBinding

class CafeColdBrew : AppCompatActivity() {

    private lateinit var binding: ActivityCafeColdBrewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var like = false
        var state = false

        binding = ActivityCafeColdBrewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAnadir.setOnClickListener {
            state = buttonAnimation(state)
        }

        binding.favoriteImageView.setOnClickListener{
            like = likeAnimation(binding.favoriteImageView, R.raw.heart_norm, like)
        }
    }

    private fun buttonAnimation(state : Boolean) : Boolean{
        if(!state){
            binding.btnAnadir.setBackgroundColor(Color.BLACK)
            binding.btnAnadir.setText(R.string.anadido)
        } else {
            binding.btnAnadir.setBackgroundColor(Color.parseColor("#A55959"))
            binding.btnAnadir.setText(R.string.anadir)
        }
        return !state
    }

    private fun likeAnimation(imageView: LottieAnimationView, animation:Int, like: Boolean) : Boolean{

        if(!like){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate().alpha(0f).setDuration(200).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        imageView.setImageResource(R.drawable.twitter_like)
                        imageView.alpha = 1f
                    }
                })
            imageView.setImageResource(R.drawable.twitter_like)
        }
        return !like
    }
}