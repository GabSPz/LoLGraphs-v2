package com.example.lolgraphs.core.di

import android.content.Context
import androidx.room.Ignore
import androidx.room.Room
import com.example.lolgraphs.data.database.ChampDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private val CHAMP_DATABASE_NAME = "champ_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ChampDatabase::class.java,CHAMP_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideChampDao(db: ChampDatabase) = db.getChampDao()
}