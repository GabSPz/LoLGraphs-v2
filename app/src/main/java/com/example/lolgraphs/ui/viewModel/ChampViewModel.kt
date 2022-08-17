package com.example.lolgraphs.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Ignore
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.GetChampUseCase
import com.example.lolgraphs.domain.favoritemodel.ChampFavoriteModel
import com.example.lolgraphs.domain.model.ChampModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class ChampViewModel @Inject constructor(
    private val getChampUseCase :GetChampUseCase
    ): ViewModel() {

    val champModel = MutableLiveData< Map<String, ChampModel> >()
    val champDc = MutableLiveData< Map<String, ChampionDc> >()
    val champFav = MutableLiveData< Map<String, ChampModel> >()
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

    fun sendFavoriteChamp(fav: Boolean){
        viewModelScope.launch {
            if (fav){
                val champMap = mutableMapOf<String,ChampModel>()
                val resultFav =getChampUseCase.getFavoriteChamp()
                champMap.putAll(resultFav.associateBy { it.name })
                champFav.postValue(champMap)
                println(champMap)
            }else{
                getChampUseCase.deleteChamp()
            }
        }
    }


}