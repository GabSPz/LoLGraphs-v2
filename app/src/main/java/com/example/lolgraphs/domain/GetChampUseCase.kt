package com.example.lolgraphs.domain

import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import com.example.lolgraphs.network.ChampRepository

class GetChampUseCase {

    private val  repository = ChampRepository()

    suspend operator fun invoke(query: String): ChampResponse?  = repository.getChamp(query)

}