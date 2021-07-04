package com.example.quran.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quran.databinding.ActivitySplashBinding
import com.example.quran.utils.viewBinding

class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}