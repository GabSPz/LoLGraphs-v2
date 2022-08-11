package com.example.lolgraphs.data.network.apiConsumer

import com.example.lolgraphs.data.model.ChampionDc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChampService @Inject constructor(private val apiService: APIService) {

    suspend fun getAllChamps(): Map<String, ChampionDc> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllChamp()
            response.body()?.onlyChamp ?: emptyMap()
        }
    }

    suspend fun getChamp(query: String): Map<String, ChampionDc> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getOnlyChamp(query)
            response.body()?.onlyChamp ?: emptyMap()
        }
    }
}