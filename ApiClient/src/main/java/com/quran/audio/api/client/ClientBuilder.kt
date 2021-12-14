package com.quran.audio.api.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ClientBuilder {
    fun build(baseApiUrl: String, isDebug: Boolean = false): Client {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseApiUrl)
            .client(buildOkHttpClient(isDebug))
            .build().create(Client::class.java)
    }

    private fun buildOkHttpClient(isDebug: Boolean): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if(isDebug) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }
}