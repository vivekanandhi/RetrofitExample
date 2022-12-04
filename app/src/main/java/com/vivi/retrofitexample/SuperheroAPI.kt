package com.vivi.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface SuperheroAPI {
    @GET("0c0939ad-c7a1-4d6c-bec9-bda2abcbf245")
    fun getHeroes(): Call<Heros>

}