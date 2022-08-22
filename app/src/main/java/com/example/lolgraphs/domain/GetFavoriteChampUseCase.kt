package com.example.lolgraphs.domain

import com.example.lolgraphs.data.ChampRepository
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.toDatabase
import javax.inject.Inject

class GetFavoriteChampUseCase @Inject constructor( private val repository: ChampRepository){

    suspend fun getFavoriteChamp(champModel: ChampModel): Map<String, ChampModel>{

        val champs = mutableMapOf<String, ChampModel>()
        val champsDb = repository.getFavoriteChampOfDb()
        repository.insertFavoriteChamps(champModel.toDatabase())
        for (i in champsDb.indices){
            val oneChamp = champsDb[i]
            champs[oneChamp.name] = oneChamp
        }
        return champs
    }

    suspend fun getChampsOfDatabase():  Map<String, ChampModel> {
        val champs = mutableMapOf<String, ChampModel>()
        val champsDb = repository.getFavoriteChampOfDb()
        for (i in champsDb.indices) {
            val oneChamp = champsDb[i]
            champs[oneChamp.name] = oneChamp
        }
        return champs
    }

    suspend fun deleteChamp(champId: String){
        repository.clearChamp(champId)
    }
}