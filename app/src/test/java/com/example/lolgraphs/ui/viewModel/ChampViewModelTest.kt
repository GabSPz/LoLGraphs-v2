package com.example.lolgraphs.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.data.model.subModel.ImageChamp
import com.example.lolgraphs.data.model.subModel.Info
import com.example.lolgraphs.data.model.subModel.Stats
import com.example.lolgraphs.domain.GetChampUseCase
import com.example.lolgraphs.domain.GetFavoriteChampUseCase
import com.example.lolgraphs.domain.model.ChampModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ChampViewModelTest{

    @RelaxedMockK
    private lateinit var getChampUseCase : GetChampUseCase

    @RelaxedMockK
    private lateinit var getFavoriteChampUseCase: GetFavoriteChampUseCase

    private lateinit var champViewModel: ChampViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        champViewModel = ChampViewModel( getChampUseCase, getFavoriteChampUseCase )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all champs from api and set all champs on live data`() = runTest {
        //Given
        val champMap= mapOf(Pair("TEST",ChampModel("TESTING", "TEST")), Pair("TEST 2",ChampModel("TESTING 2", "TEST 2")))
        coEvery { getChampUseCase.getAllChamp() } returns champMap

        //When
        champViewModel.onCreate()

        //Then
        assert(champViewModel.champModel.value == champMap)
    }

    @Test
    fun `when the favorite check box is checked, insert champ in data base`() = runTest {
        //Given
        val champTest = mapOf(Pair("TEST",ChampModel("TESTING", "TEST")))
        coEvery { getFavoriteChampUseCase.getFavoriteChamp(any()) } returns champTest

        //When
        champViewModel.onFavoriteChamp(true, champTest.values.first())

        //Then
        assert( champViewModel.champModel.value == champTest )
    }

    @Test
    fun `when the favorite check box is not checked, delete champ from data base`() = runTest {
        //Given
        val champTest = ChampModel("TESTING", "TEST")
        coEvery { getFavoriteChampUseCase.deleteChamp(champTest.id) }

        //When
        champViewModel.onFavoriteChamp(false, champTest)

        //Then
        coVerify (exactly = 1 ){ getFavoriteChampUseCase.deleteChamp(champTest.id) }
    }

    @Test
    fun `when the favorite fragment init, get all champs favorites from data base`() = runTest {
        //Given
        val champMap= mapOf(Pair("TEST",ChampModel("TESTING", "TEST")), Pair("TEST 2",ChampModel("TESTING 2", "TEST 2")))
        coEvery { getFavoriteChampUseCase.getChampsOfDatabase() } returns champMap

        //When
        champViewModel.getFavoriteChamp()

        //Then
        assert(champViewModel.champModel.value == champMap)
    }

    @Test
    fun `when the user click in one item of the recycler view, get all champ data from Api`(){
        //Given
        val champTest = mapOf(Pair("TEST", ChampionDc("1","TESTING","AD", "TEST", "ADA", "ADA", "AE",
            Info(1,2,1,1), ImageChamp("A","B" ,"C",1,1,1,1), skins = null, enemyTips = null, tags = listOf("A","B"), partype = "P",
            Stats(1F,2f,3f,1f,2f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f)
        )))
        coEvery { getChampUseCase.getOneChamp("TEST") } returns champTest

        // When
        champViewModel.onSearch("TEST")

        //Then
        assert(champViewModel.champDc.value == champTest)
    }
}