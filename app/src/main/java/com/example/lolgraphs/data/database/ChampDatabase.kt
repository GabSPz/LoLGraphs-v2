package com.example.lolgraphs.data.database

import androidx.room.Database
import androidx.room.Ignore
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lolgraphs.data.database.dao.ChampDao
import com.example.lolgraphs.data.database.entities.ChampEntity


@Database(entities = [ChampEntity::class], version = 1)
abstract class ChampDatabase: RoomDatabase() {

    abstract fun getChampDao():ChampDao
}