package com.example.lolgraphs.data.database.entities

import androidx.room.*
import com.example.lolgraphs.domain.model.ChampModel

@Entity(tableName = "champ_table")
data class ChampEntity (
    @ColumnInfo(name = "version") val version: String,
    @ColumnInfo(name = "id") val champId:String,
    @ColumnInfo(name = "key") val key:String,
    @ColumnInfo(name = "name") val name:String,
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

fun ChampModel.toDatabase() = ChampEntity(
    version = version,
    champId = id,
    key = key,
    name = name,

)
