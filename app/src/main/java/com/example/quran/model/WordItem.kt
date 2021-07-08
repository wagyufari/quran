package com.example.quran.model

data class WordItem(
	val line_number: Int? = null,
	val id: Int? = null,
	val position: Int? = null,
	var verse_key:String?=null,
	val code_v1: String? = null,
	var page:Int?=null,
	var chapter_number:Int,
	val is_bismillah:Boolean?=false
)

data class SeparatorItem(val page:Int, val surah:Int?=null, val line:Int, val bismillah:Boolean?=false, val unicode:String?="")

data class VersesItem(
	val words: List<WordItem> = arrayListOf(),
	val id: Int? = null,
	val verse_number: Int? = null,
	val verse_key:String?=null,
	val juz_number: Int? = null,
	var page:Int?=null,
	val hizb_number: Int? = null,
)
