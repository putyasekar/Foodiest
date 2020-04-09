package com.diki.idn.foodiest.sources

import com.diki.idn.foodiest.common.Constanta
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private val retrofitConfiguration by lazy {
        Retrofit.Builder().baseUrl(Constanta().BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiServiceClient: ApiService by lazy {
        retrofitConfiguration.create(ApiService::class.java)
    }
}