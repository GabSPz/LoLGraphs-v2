package com.example.lolgraphs.domain.model.submodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//@Entity//(tableName = "imageChamp_table")
data class ImageChampModel constructor(
    //@PrimaryKey(autoGenerate = true) val imageChampId: Int = 0,
   // @ColumnInfo(name = "full")
    val imagePerChamp:String,
    //@ColumnInfo(name = "sprite")
    val sprite:String,
    //@ColumnInfo(name = "group")
    val group:String,
    //@ColumnInfo(name = "x") var x:Int,
    //@ColumnInfo(name = "y") var y:Int,
    //@ColumnInfo(name = "w") var w:Int,
    //@ColumnInfo(name = "h") var h:Int,
)