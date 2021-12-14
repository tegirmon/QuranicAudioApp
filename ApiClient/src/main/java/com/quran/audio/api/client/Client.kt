package com.quran.audio.api.client

import com.quran.audio.api.client.model.AudioFile
import com.quran.audio.api.client.model.Reciter
import com.quran.audio.api.client.model.Section
import com.quran.audio.api.client.model.Sura
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface Client {

    @GET("qaris")
    fun getReciters(): Observable<List<Reciter>>

    @GET("qaris/{reciterId}")
    fun getReciter(@Path("reciterId") reciterId: Int): Observable<Reciter>

    @GET("sections")
    fun getSections(): Observable<List<Section>>

    @GET("surahs/{suraNumber}")
    fun getSura(@Path("suraNumber") suraNumber: Int): Observable<Sura>

    @GET("surahs")
    fun getSuras(): Observable<List<Sura>>

    @GET("audio_files/{suraNumber}")
    fun getAudioFile(@Path("suraNumber") suraNumber: Int): Observable<AudioFile>

    @GET("audio_files")
    fun getAudioFiles(): Observable<List<AudioFile>>

}