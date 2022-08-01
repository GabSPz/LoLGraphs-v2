package com.example.lolgraphs.network.apiConsumer.Responses

import com.example.lolgraphs.data.model.ChampionDc
import com.google.gson.annotations.SerializedName

data class ChampResponse(
    @SerializedName("type") val type:String,
    @SerializedName("format") val format:String,
    @SerializedName("version") val version: String,
    @SerializedName("data") val onlyChamp:Map<String, ChampionDc>
)
