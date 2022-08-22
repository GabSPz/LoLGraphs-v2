package com.example.lolgraphs.data.db.dao

import androidx.room.*
import com.example.lolgraphs.data.db.entities.ChampEntity

@Dao
interface ChampDao {

    @Query("SELECT * FROM champ_table ORDER BY name ASC")
    suspend fun getAllFavoriteChamps():List<ChampEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champsFav:ChampEntity)

    @Query("Delete From champ_table Where id == :champID")
    suspend fun deleteAllChamp(champID: String)
}