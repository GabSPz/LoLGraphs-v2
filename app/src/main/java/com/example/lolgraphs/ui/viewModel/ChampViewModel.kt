package com.example.lolgraphs.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Ignore
import com.example.lolgraphs.domain.GetChampUseCase
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampViewModel @Ignore @Inject constructor(
    private val getChampUseCase :GetChampUseCase
    ): ViewModel() {

    val champModel = MutableLiveData< Map<String, ChampModel> >()
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
                 champModel.postValue(result)
                isLoading.postValue(false)
            }
            else{
                error("ERROR")
            }
        }
    }

    fun sendItemSelected(){

    }


}