package com.example.lolgraphs.network.apiConsumer

import com.example.lolgraphs.core.RetrofitChamp
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChampService {

    private val getAllChamp = RetrofitChamp.getChampCore()

    suspend fun getChamp (query: String): ChampResponse? {
        return withContext(Dispatchers.IO) {
            val response = getAllChamp.create(APIService::class.java).getAllChamp(query)
            response.body()
        }
    }
}