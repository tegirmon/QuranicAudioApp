package com.quran.audio.api.client.model

import com.google.gson.annotations.SerializedName

data class AudioFile(
    @SerializedName("qari_id")
    val reciterId: Int,
    @SerializedName("surah_id")
    val suraNumber: Int,
    @SerializedName("main_id")
    val mainId: Int,
    @SerializedName("recitation_id")
    val recitationId: Int,
    @SerializedName("filenum")
    val fileNumber: Int,
    @SerializedName("file_name")
    val fileName: String,
    val extension: String,
    @SerializedName("stream_count")
    val streamCount: Int,
    @SerializedName("download_count")
    val downloadCount: Int,
    @SerializedName("qari")
    val reciter: Reciter
)
