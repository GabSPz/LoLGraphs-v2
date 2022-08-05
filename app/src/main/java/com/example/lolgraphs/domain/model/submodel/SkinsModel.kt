package com.example.lolgraphs.domain.model.submodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//@Entity
data class SkinsModel  constructor(
    //@ColumnInfo(name = "id")
    val idSkin: String,
    //@ColumnInfo(name = "num")
    val num:Int,
    //@ColumnInfo(name = "name")
    val nameSkin:String
)
