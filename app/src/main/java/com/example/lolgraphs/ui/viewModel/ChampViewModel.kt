package com.example.lolgraphs.ui.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Ignore
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.domain.GetChampUseCase
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.network.apiConsumer.Responses.ChampResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampViewModel @Ignore @Inject constructor(
    private val getChampUseCase :GetChampUseCase
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

    fun sendItemSelected(){

    }


}
/*<androidx.appcompat.widget.SearchView
            android:id="@+id/svChamps"
            android:visibility="gone"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:layout_constraintVertical_bias="0"
            android:text="Hello World!"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recycleChamps"
            android:visibility="gone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginHorizontal="16dp"
            android:layout_marginVertical="8dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/svChamps" />

        <ProgressBar
            android:id="@+id/progress"
            android:visibility="gone"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.1"/>

 */