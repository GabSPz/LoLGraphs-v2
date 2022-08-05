package com.example.lolgraphs.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitChamp {
    fun getChampCore(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ddragon.leagueoflegends.com/cdn/12.12.1/data/es_MX/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}