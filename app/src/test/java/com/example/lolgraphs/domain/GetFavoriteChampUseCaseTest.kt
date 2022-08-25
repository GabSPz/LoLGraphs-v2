package com.example.lolgraphs.domain

import com.example.lolgraphs.data.ChampRepository
import com.example.lolgraphs.domain.model.ChampModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavoriteChampUseCaseTest{
    @RelaxedMockK
    private lateinit var  champRepository: ChampRepository

    lateinit var getFavoriteChampUseCase: GetFavoriteChampUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFavoriteChampUseCase = GetFavoriteChampUseCase(champRepository)
    }

    @Test
     fun `when the favorite fragment init then get all champs from data base`() = runBlocking {
        //Given
        coEvery {  champRepository.getFavoriteChampOfDb() } returns emptyList()

        //When
        getFavoriteChampUseCase.getChampsOfDatabase()

        //Then
        coVerify (exactly = 1) { champRepository.getFavoriteChampOfDb() }
        coVerify (exactly = 0) { champRepository.insertFavoriteChamps(any()) }

    }

    @Test
    fun `when the user does click on favorite check box then insert champ to data base `() = runBlocking {
        //Given
        val testList = listOf(ChampModel("TEST", "TESTED"))
        coEvery { champRepository.getFavoriteChampOfDb() } returns listOf(testList.first())

        //When
        val response = getFavoriteChampUseCase.getFavoriteChamp(testList.first())

        //Then
        coVerify { champRepository.insertFavoriteChamps(any()) }
        assert( response.containsValue(testList.first()) )
    }

}