package com.quran.audio.app.data

import com.quran.audio.api.client.ClientBuilder
import com.quran.audio.api.client.model.AudioFile
import com.quran.audio.api.client.model.Reciter
import com.quran.audio.api.client.model.Section
import com.quran.audio.api.client.model.Sura
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataSource(baseApiUrl: String) {
    private val clientApi = ClientBuilder.build(baseApiUrl)

    fun getReciters(): Observable<List<Reciter>> {
        return clientApi.getReciters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSections(): Observable<List<Section>> {
        return clientApi.getSections()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSuras(): Observable<List<Sura>> {
        return clientApi.getSuras()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAudioFiles(): Observable<List<AudioFile>> {
        return clientApi.getAudioFiles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}