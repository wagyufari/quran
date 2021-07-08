package com.example.quran.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quran.R
import com.example.quran.databinding.ActivityMainBinding
import com.example.quran.ui.adapter.FragmentPagerAdapter
import com.example.quran.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pager.apply {
            val pages = arrayListOf<Int>().apply {
                for (i in 1..604) {
                    add(i)
                }
            }.reversed().toCollection(arrayListOf())
            val fragments = pages.map { page->
                QuranPagedFragment(page)
            }
            adapter = FragmentPagerAdapter(this@MainActivity, fragments.toCollection(arrayListOf()))
            setCurrentItem(pages.indexOf(293), false)
        }

    }
}