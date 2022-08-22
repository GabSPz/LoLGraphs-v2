package com.example.lolgraphs.domain.model

import com.example.lolgraphs.data.db.entities.ChampEntity
import com.example.lolgraphs.data.model.ChampionDc



data class ChampModel(
    val id:String,
    val name:String
)

fun ChampionDc.toDomain() = ChampModel(
    id,
    name
)
fun ChampEntity.toDomain() = ChampModel(
    name =  name,
    id = id
)

fun ChampModel.toDatabase() = ChampEntity(
    name = name,
    id = id
)

