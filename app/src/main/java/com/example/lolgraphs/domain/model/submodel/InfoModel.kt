package com.example.lolgraphs.domain.model.submodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//@Entity
data class InfoModel  constructor(
    //@ColumnInfo(name = "attack")
    val attack:Int,
    //@ColumnInfo(name = "defense")
    val defense:Int,
    //@ColumnInfo(name = "magic")
    val magic:Int,
    //@ColumnInfo(name = "difficulty")
    val difficulty:Int
)

