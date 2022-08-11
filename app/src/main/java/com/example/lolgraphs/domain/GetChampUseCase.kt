package com.example.lolgraphs.domain


import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import com.example.lolgraphs.network.ChampRepository
import javax.inject.Inject

class GetChampUseCase @Inject constructor(private val  repository: ChampRepository){

    suspend fun getOneChamp(query: String): Map<String, ChampionDc> {
        val champs = repository.getChamp(query)
        return champs
    }

    suspend fun getAllChamp():Map<String, ChampModel> {
        val champs = repository.getAllChamps()
        return champs

    }

}


