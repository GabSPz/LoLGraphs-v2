package com.example.lolgraphs.domain


import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.data.ChampRepository
import javax.inject.Inject

class GetChampUseCase @Inject constructor(private val  repository: ChampRepository){

    suspend fun getOneChamp(query: String): Map<String, ChampionDc> = repository.getChamp(query)

    suspend fun getAllChamp():Map<String, ChampModel> = repository.getAllChamps()
}


