package com.example.lolgraphs.core.di

import android.content.Context
import androidx.room.Room
import com.example.lolgraphs.data.db.ChampDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val FAVORITE_CHAMP_DB_NAME = "favorite_champ_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,ChampDataBase::class.java, FAVORITE_CHAMP_DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideChampDao(db : ChampDataBase) = db.getChampDao()

}