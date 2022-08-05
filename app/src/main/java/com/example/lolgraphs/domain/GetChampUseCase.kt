package com.example.lolgraphs.domain

import androidx.room.Ignore
import com.example.lolgraphs.data.database.entities.toDatabase
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import com.example.lolgraphs.network.ChampRepository
import javax.inject.Inject

class GetChampUseCase @Inject constructor(private val  repository: ChampRepository){

    suspend fun getOneChamp(query: String): Map<String, ChampModel> {
        val champs = repository.getChamp(query)
        return if (champs.isNotEmpty()){
            //repository.clearDatabase()
            //repository.insertChamps(champs.mapValues { it.component2().toDatabase() })
            champs
        }else{
            emptyMap()
        }

    }

    suspend fun getAllChamp():Map<String, ChampModel> {
        val champs = repository.getAllChamps()
        return if (champs.isNotEmpty() ){
            //repository.clearDatabase()
            //repository.insertChamps(champs.mapValues { it.component2().toDatabase() })
             champs
        }else{
             emptyMap()
        }
    }



}