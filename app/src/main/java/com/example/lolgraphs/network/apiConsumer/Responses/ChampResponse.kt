package com.example.lolgraphs.network.apiConsumer.Responses

import com.example.lolgraphs.data.model.ChampionDc
import com.google.gson.annotations.SerializedName

data class ChampResponse(
    @SerializedName("type") var type:String,
    @SerializedName("format") var format:String,
    @SerializedName("version") var version: String,
    @SerializedName("data") var onlyChamp:Map<String, ChampionDc>
)
