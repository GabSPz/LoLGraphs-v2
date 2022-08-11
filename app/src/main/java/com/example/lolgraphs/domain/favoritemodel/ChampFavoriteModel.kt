package com.example.lolgraphs.domain.favoritemodel

import com.example.lolgraphs.data.db.entities.ChampEntity
import com.example.lolgraphs.data.model.ChampionDc

data class ChampFavoriteModel(
    val name : String,
    val id: String
)

fun ChampEntity.toFavorite() = ChampFavoriteModel(
    name =  name,
    id = id
)

fun ChampFavoriteModel.toDatabase() = ChampEntity(
    name = name,
    id = id
)

fun ChampionDc.toFavorite() = ChampFavoriteModel(
    name = name,
    id = id
)