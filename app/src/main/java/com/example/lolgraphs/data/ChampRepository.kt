package com.example.lolgraphs.data


import com.example.lolgraphs.data.db.dao.ChampDao
import com.example.lolgraphs.data.db.entities.ChampEntity
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.favoritemodel.ChampFavoriteModel
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.toDomain
import com.example.lolgraphs.data.network.apiConsumer.ChampService
import javax.inject.Inject

class ChampRepository @Inject constructor (
    private val api: ChampService,
    private val champDao: ChampDao
    ){

    suspend fun getAllChamps(): Map<String, ChampModel> {
        val response = api.getAllChamps()
        return response.mapValues { it.value.toDomain() }

    }

    //onChampResultActivity
    suspend fun getChamp(query: String): Map<String, ChampionDc> {
        val response = api.getChamp(query)
        return response//.mapValues { it.component2().toDomain() }
    }

    suspend fun getFavoriteChampOfDb():List<ChampModel>{
        val response = champDao.getAllFavoriteChamps()
        return response.map { it.toDomain() }
    }

    suspend fun insertFavoriteChamps(champs : ChampEntity){
        champDao.insertAll(champs)
    }

    suspend fun clearChamp(id:String){
        champDao.deleteAllChamp()
    }
}

