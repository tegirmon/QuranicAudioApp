package com.quran.audio.app.data.model

data class ReciterModel(
    val id: Int,
    val name: String,
    val relativePath: String,
    val fileFormats: String,
    val sectionId: Int,
    val description: String
)
