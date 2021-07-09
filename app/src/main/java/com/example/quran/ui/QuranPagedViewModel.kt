package com.example.quran.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quran.model.SeparatorItem
import com.example.quran.model.VersesItem
import com.example.quran.model.WordItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class QuranPagedViewModel:ViewModel() {

    fun getVerseByLine(line: Int, words: List<WordItem>): String {
        return words.filter { it.line_number == line }.sortedBy { it.id }.map { it.code_v1 }
            .joinToString("")
    }

    suspend fun getSeparatorByLineAndPage(context:Context, page:Int, line:Int): SeparatorItem?{
        return getSeparator(context).filter { it.page == page && it.line == line }.firstOrNull()
    }

    suspend fun getSeparator(context:Context): List<SeparatorItem>{
        return suspendCoroutine { suspended->
            context.assets.open("json/separator.json").bufferedReader().use {
                val separator = Gson().fromJson<ArrayList<SeparatorItem>>(it.readLine(), object :
                    TypeToken<ArrayList<SeparatorItem>>() {}.type)
                suspended.resume(separator)
            }
        }
    }

    suspend fun getWords(context: Context, page: Int): List<WordItem> {
        return suspendCoroutine { suspended ->
            context.assets.open("json/page$page.json").bufferedReader().use {
                val verses = Gson().fromJson<ArrayList<VersesItem>>(it.readLine(), object :
                    TypeToken<ArrayList<VersesItem>>() {}.type)
                suspended.resume(verses.map { it.words }.flatten())
            }
        }
    }

}