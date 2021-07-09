package com.example.quran.ui

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quran.R
import com.example.quran.databinding.FragmentQuranPagedBinding
import com.example.quran.databinding.LineSurahHeaderBinding
import com.example.quran.model.SeparatorItem
import com.example.quran.model.VersesItem
import com.example.quran.model.WordItem
import com.example.quran.utils.dpToPx
import com.example.quran.utils.io
import com.example.quran.utils.main
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class QuranPagedFragment(val page: Int) : Fragment() {

    lateinit var binding: FragmentQuranPagedBinding
    val viewModel: QuranPagedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran_paged, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        io {
            val words = viewModel.getWords(requireActivity(),page)
            for (i in 1..15) {
                val verse = viewModel.getVerseByLine(i, words)
                main {
                    if (verse.isBlank()) {
                        viewModel.getSeparatorByLineAndPage(requireActivity(), page, i)?.let {
                            if (it.bismillah == true){
                                binding.container.addView(getBismillah())
                            } else if (it.surah != null) {
                                binding.container.addView(getSurah(it.unicode.toString()))
                            }
                        }?: kotlin.run {
                            binding.container.addView(getBlank())
                        }
                    } else{
                        binding.container.addView(getLine(page, verse))
                    }
                }
            }
        }
    }

    fun getBismillah(): View {
        return TextView(requireActivity()).apply {
//            typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/Bismillah.ttf")
            text = "﷽"
            setTextColor(resources.getColor(R.color.textPrimary))
            TextViewCompat.setAutoSizeTextTypeWithDefaults(
                this,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
            )
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0
            ).apply {
                weight = 0.8f
                setPadding(dpToPx(2))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun getSurah(surah: String): View {
        return LineSurahHeaderBinding.inflate(LayoutInflater.from(requireContext())).apply {
            text.text = surah + "\uE903"
            text.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/Surah.ttf")
        }.root
    }

    fun getBlank(): View {
        return View(requireActivity()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0
            ).apply {
                weight = 1f
            }
        }
    }

    fun getLine(page: Int, verse: String): View {
        return TextView(requireActivity()).apply {
            typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/quran/p$page.ttf")
            text = verse
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0
            ).apply {
                weight = 1f
            }
            setTextIsSelectable(true)
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            setTextColor(requireContext().resources.getColor(R.color.textPrimary))
            TextViewCompat.setAutoSizeTextTypeWithDefaults(
                this,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
            )
        }
    }

}