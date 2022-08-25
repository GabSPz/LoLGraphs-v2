package com.example.lolgraphs.domain

import com.example.lolgraphs.data.ChampRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetChampUseCaseTest{

    @RelaxedMockK
    private lateinit var  champRepository: ChampRepository

    lateinit var getChampUseCase: GetChampUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getChampUseCase = GetChampUseCase(champRepository)
    }
    @Test
    fun `when The Home Fragment Do Init Then Get All Champs From Api`() = runBlocking {
        //Given
        coEvery { champRepository.getAllChamps() } returns emptyMap()

        //When
        getChampUseCase.getAllChamp()

        //Then
        coVerify (exactly = 1) { champRepository.getAllChamps() }
    }

    @Test
    fun `when The User Does Click On Any Recycler View Then Get One Champ From Api`() = runBlocking {
        //Given
        val randomName = "NAME_TEST"
        coEvery { champRepository.getChamp(randomName) } returns emptyMap()
        
        //When
        val response = getChampUseCase.getOneChamp(randomName)

        //Then
        coVerify (exactly = 1) { champRepository.getChamp(any()) }
        coVerify (exactly = 0) { champRepository.getAllChamps() }

        assert(response.isEmpty())
    }

}