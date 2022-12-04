package com.vivi.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface SuperheroAPI {
    @GET("61eb3e08-57ae-4401-b398-d29295b2832b")
    fun getHeroes(): Call<List<Heros>>

}