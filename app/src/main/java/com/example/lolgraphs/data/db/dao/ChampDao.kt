package com.example.lolgraphs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lolgraphs.data.db.entities.ChampEntity

@Dao
interface ChampDao {

    @Query("SELECT * FROM champ_table ORDER BY name ASC")
    suspend fun getAllFavoriteChamps():List<ChampEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champsFav:ChampEntity)

    @Query ("DELETE FROM champ_table")
    suspend fun deleteAllChamp()
}