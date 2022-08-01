package com.example.lolgraphs.network.apiConsumer

import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("champion{query}json")
         suspend fun getAllChamp(@Path("query") query :String):Response<ChampResponse>
}