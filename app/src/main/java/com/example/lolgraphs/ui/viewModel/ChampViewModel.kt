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
    val isLoading = MutableLiveData<Boolean>()

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

    fun onFavoriteChamp(fav: Boolean, championModel: ChampModel){
        viewModelScope.launch {
            if (fav){
                val result = getFavoriteChampUseCase.getFavoriteChamp(championModel)
                champModel.postValue(result)
            }else{
                getFavoriteChampUseCase.deleteChamp(championModel.id)
            }
        }
    }

    suspend fun getFavoriteChamp (){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFavoriteChampUseCase.getChampsOfDatabase()
            champModel.postValue(result)
            isLoading.postValue(false)
        }
    }


}