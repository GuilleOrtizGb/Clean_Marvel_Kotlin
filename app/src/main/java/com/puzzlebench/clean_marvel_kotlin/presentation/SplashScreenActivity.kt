package com.puzzlebench.clean_marvel_kotlin.presentation

import android.app.Activity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.View
import android.widget.ImageView
import android.content.Intent

class SplashScreenActivity: Activity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.puzzlebench.clean_marvel_kotlin.R.layout.splash_screen)

        val imageView = findViewById<View>(com.puzzlebench.clean_marvel_kotlin.R.id.imageView) as ImageView
        val rotationAnimation = AnimationUtils.loadAnimation(baseContext, com.puzzlebench.clean_marvel_kotlin.R.anim.rotation)
        val stopAnimation = AnimationUtils.loadAnimation(baseContext, com.puzzlebench.clean_marvel_kotlin.R.anim.abc_fade_out)

        imageView.startAnimation(rotationAnimation)
        rotationAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                imageView.startAnimation(stopAnimation)
                finish()
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}