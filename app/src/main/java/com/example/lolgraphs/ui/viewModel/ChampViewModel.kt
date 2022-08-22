package com.example.lolgraphs.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.GetChampUseCase
import com.example.lolgraphs.domain.GetFavoriteChampUseCase
import com.example.lolgraphs.domain.model.ChampModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChampViewModel @Inject constructor(
    private val getChampUseCase :GetChampUseCase,
    private val getFavoriteChampUseCase: GetFavoriteChampUseCase
    ): ViewModel() {

    val champModel = MutableLiveData< Map<String, ChampModel> >()
    val champDc = MutableLiveData< Map<String, ChampionDc> >()
    val champFav = MutableLiveData< Map<String, ChampModel> >()
    val isLoading = MutableLiveData<Boolean>()
    val isLoadingFav = MutableLiveData<Boolean>()

    fun onCreate (){
        viewModelScope.launch {
            isLoading.postValue(true)
            val resultChamps = getChampUseCase.getAllChamp()
            if (resultChamps.isNotEmpty()){
                champModel.postValue(resultChamps)
                isLoading.postValue(false)
            }
        }
    }
    fun onSearch(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getChampUseCase.getOneChamp(query)

            if (!result.isNullOrEmpty()) {
                 champDc.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun onFavoriteChamp(fav: Boolean, champModel: ChampModel){
        viewModelScope.launch {
            if (fav){
                val resultFav =getFavoriteChampUseCase.getFavoriteChamp(champModel)
                champFav.postValue(resultFav)
            }else{
                getFavoriteChampUseCase.deleteChamp(champModel.id)
            }
        }
    }

    suspend fun getFavoriteChamp (){
        isLoadingFav.postValue(true)
        val result = getFavoriteChampUseCase.getChampsOfDatabase()
        champFav.postValue(result)
        isLoadingFav.postValue(false)
    }


}