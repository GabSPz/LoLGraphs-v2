package com.example.lolgraphs.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champ_table")
data class ChampEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "uid")val uid: Int = 0,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo (name = "id") val id : String

    )
