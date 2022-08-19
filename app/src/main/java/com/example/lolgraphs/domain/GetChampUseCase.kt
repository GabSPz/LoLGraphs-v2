package com.example.lolgraphs.domain


import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.favoritemodel.ChampFavoriteModel
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.data.ChampRepository
import com.example.lolgraphs.domain.model.toDatabase
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

    suspend fun deleteChamp(){
            repository.clearChamp()
    }



}


