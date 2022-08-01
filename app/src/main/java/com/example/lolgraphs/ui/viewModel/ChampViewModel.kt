package com.example.lolgraphs.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lolgraphs.domain.GetChampUseCase
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import kotlinx.coroutines.launch

class ChampViewModel : ViewModel() {

    val champModel = MutableLiveData<ChampResponse?>()
    val getChampUseCase = GetChampUseCase()

    fun onCreate(query: String) {
        viewModelScope.launch {
            val result = getChampUseCase(query)

            if (!result?.onlyChamp.isNullOrEmpty()) {
                 champModel.postValue(result)
            }
            else{
                error("ERROR")
            }
        }
    }

    fun sendItemSelected(){

    }


}