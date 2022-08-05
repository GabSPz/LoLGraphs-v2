package com.example.lolgraphs.data.database.dao

import androidx.room.*
import com.example.lolgraphs.data.database.entities.ChampEntity

@Dao
interface ChampDao {
    @Query("SELECT * FROM champ_table ORDER BY id DESC")
    suspend fun getAllChamp():Map<String,ChampEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champs: Map<String,ChampEntity>)

    @Query("DELETE FROM champ_table")
    suspend fun deletesAllData()

}