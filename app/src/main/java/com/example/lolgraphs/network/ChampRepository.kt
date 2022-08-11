package com.example.lolgraphs.network


//import com.example.lolgraphs.domain.favoritemodel.ChampFavoriteModel
//import com.example.lolgraphs.domain.favoritemodel.toFavorite
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.toDomain
import com.example.lolgraphs.network.apiConsumer.ChampService
import javax.inject.Inject

class ChampRepository @Inject constructor (private val api: ChampService){

    suspend fun getAllChamps(): Map<String, ChampModel> {
        val response = api.getAllChamps()
        return response.mapValues { it.value.toDomain() }

    }

    //onChampResultActivity
    suspend fun getChamp(query: String): Map<String, ChampionDc> {
        val response = api.getChamp(query)
        return response//.mapValues { it.component2().toDomain() }
    }
}

