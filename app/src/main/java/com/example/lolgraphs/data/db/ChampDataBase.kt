package com.example.lolgraphs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lolgraphs.data.db.dao.ChampDao
import com.example.lolgraphs.data.db.entities.ChampEntity

@Database(entities = [ChampEntity::class], version = 1)
abstract class ChampDataBase : RoomDatabase() {
    abstract fun getChampDao():ChampDao
}