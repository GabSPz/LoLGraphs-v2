package com.example.lolgraphs.network

import com.example.lolgraphs.data.database.dao.ChampDao
import com.example.lolgraphs.data.database.entities.ChampEntity
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.favoritemodel.ChampFavoriteModel
import com.example.lolgraphs.domain.favoritemodel.toFavorite
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.toDomain
import com.example.lolgraphs.network.apiConsumer.ChampService
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class ChampRepository @Inject constructor(
    private val api: ChampService,
    private val champDao: ChampDao
    ) {

    suspend fun getAllChamps(): Map<String, ChampModel>{
        val response = api.getAllChamps().toMutableMap()
        return response.mapValues { it.component2().toDomain()}
    }

    suspend fun getAllChampsFromDatabase(): List<ChampFavoriteModel> {
        val response = champDao.getAllChamp()
        return response.map { (it.component2().toFavorite()) }
    }

    //onChampResultActivity
    suspend fun getChamp (query :String): Map<String, ChampModel>{
        val response = api.getChamp(query).toMutableMap()
        return response.mapValues { it.component2().toDomain() }
    }

    suspend fun insertChamps(champs: Map<String,ChampEntity>){
        champDao.insertAll(champs)
    }

    suspend fun clearDatabase(){
        champDao.deletesAllData()
    }
}