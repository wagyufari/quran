package com.example.quran.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quran.databinding.ActivityMainBinding
import com.example.quran.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}