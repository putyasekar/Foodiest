package com.diki.idn.foodiest.sources

import com.diki.idn.foodiest.model.food.ApiFoodResponse
import retrofit2.http.GET

interface ApiService {
    @GET("filter.php?c=Seafood")
    fun getListFood(): io.reactivex.Observable<ApiFoodResponse> //kalau mau deklrasi dengan string
//    fun getListFood(@Query("c") category: String): io.reactivex.Observable<ApiFoodResponse> //ambil observable yang reactivex
}