package com.example.lolgraphs.domain

import com.example.lolgraphs.data.ChampRepository
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.toDatabase
import javax.inject.Inject

class GetFavoriteChampUseCase @Inject constructor( private val repository: ChampRepository){

    suspend fun getFavoriteChamp(champModel: ChampModel): Map<String, ChampModel>{

        val champs = mutableMapOf<String, ChampModel>()
        val champsToDb = repository.getFavoriteChampOfDb()
        repository.insertFavoriteChamps(champModel.toDatabase())
        for (i in champsToDb.indices){
            val oneChamp = champsToDb[i]
            champs.put(oneChamp.name, oneChamp)
        }
        return champs
    }

    suspend fun getChampsOfDatabase():  Map<String, ChampModel> {
        val champs = mutableMapOf<String, ChampModel>()
        val champsToDb = repository.getFavoriteChampOfDb()
        for (i in champsToDb.indices) {
            val oneChamp = champsToDb[i]
            champs.put(oneChamp.name, oneChamp)
        }
        return champs
    }

    suspend fun deleteChamp(){
        repository.clearChamp()
    }
}