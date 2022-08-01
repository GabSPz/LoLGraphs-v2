package com.example.lolgraphs.network

import com.example.lolgraphs.network.apiConsumer.ChampService
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse

class ChampRepository {

    private val api = ChampService()

    suspend fun getChamp (query :String): ChampResponse?{
        val response = api.getChamp(query)
        return response
    }
}