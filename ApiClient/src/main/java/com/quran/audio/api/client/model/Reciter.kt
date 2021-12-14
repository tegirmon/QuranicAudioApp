package com.quran.audio.api.client.model

import com.google.gson.annotations.SerializedName

data class Reciter(
    val id: Int,
    val name: String,
    @SerializedName("arabic_name")
    val arabicName: String? = "",
    @SerializedName("relative_path")
    val relativePath: String,
    @SerializedName("file_formats")
    val fileFormats: String,
    @SerializedName("section_id")
    val sectionId: Int,
    val home: Boolean,
    val description: String? = ""
)