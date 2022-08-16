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

@HiltViewModel
class ChampViewModel @Inject constructor(
    private val getChampUseCase :GetChampUseCase
    ): ViewModel() {

    val champModel = MutableLiveData< Map<String, ChampModel> >()
    val champDc = MutableLiveData< Map<String, ChampionDc> >()
    val champFav = MutableLiveData< List<ChampFavoriteModel> >()
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

    fun sendFavoriteChamp(fav: Boolean, champFavoriteModel: ChampFavoriteModel){
        viewModelScope.launch {
            if (fav){
                val resultFav =getChampUseCase.getFavoriteChamp()
                champFav.postValue(resultFav)
                println(resultFav)
            }else{
                getChampUseCase.deleteChamp()
            }
        }
    }

    //fun viewFavoriteChamp()


}
/*<
package com.example.lolgraphs.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolgraphs.R
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.ui.view.adapter.ChampAdapter
import com.example.lolgraphs.databinding.ActivityMainBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    //private val champViewModel: ChampViewModel by viewModels()
//
    //private val championMap = mutableMapOf<String, ChampModel>()
    //private lateinit var adapter: ChampAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navHome,
                R.id.navFavorite
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.bnvNavigation,navHostFragment.navController )

        /*
        val navView: BottomNavigationView = binding.bnvNavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navHome, R.id.navFavorite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

         */
    }
}
    /*
    champViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
        callServiceGetUsers()
        binding.svChamps.setOnQueryTextListener(this)
        //
    private fun callServiceGetUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            champViewModel.onCreate()
            runOnUiThread {
                champViewModel.champModel.observe(this@MainActivity, Observer {
                    val champs = it?.toMutableMap() ?: emptyMap()
                    championMap.clear()
                    championMap.putAll(champs)
                    initRecycleView(championMap)
                    adapter.notifyDataSetChanged()
                    binding.recycleChamps.isVisible = true
                    binding.svChamps.isVisible = true
                })
            }
        }
    }

    private fun initRecycleView(map: Map<String,ChampModel>){
        adapter = ChampAdapter(map){champion -> onItemSelected(champion)}
        binding.recycleChamps.layoutManager = LinearLayoutManager(this)
        binding.recycleChamps.adapter = adapter
    }

    private fun onItemSelected(champion: ChampModel){
        //go to champ result
        val intent = Intent(this, ChampResultActivity::class.java).apply {
            putExtra("namechamp",champion.name)
        }
        startActivity(intent)
    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    //When the user use the search view, we coding the backend

    private fun searchChampionById(query: String){
        val champModel = championMap[query]
        if (!champModel?.id.isNullOrEmpty()) {
            championMap.clear()
            championMap[query]
            initRecycleView(championMap)
            adapter.notifyDataSetChanged()

        }else{
            showError()
        }
        hideKeyBoard()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            val upperQuery = query.first().uppercase()
            val lowerQuery = query.lowercase()
            val finalQuery = lowerQuery.replaceFirstChar { upperQuery }
            searchChampionById(finalQuery)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()){
            if (binding.recycleChamps.size == 1) {
                binding.recycleChamps.isVisible = false
                callServiceGetUsers()
            }
        }
        return true
    }

    private fun hideKeyBoard(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.container.windowToken, 0)
    }

}
*/

 */

/*
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

        <com.google.android.material.bottomnavigation.BottomNavigationView
            android:id="@+id/bnvNavigation"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginStart="0dp"
            android:layout_marginEnd="0dp"
            android:background="?android:attr/windowBackground"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:menu="@menu/nav_menu"/>

        <androidx.fragment.app.FragmentContainerView
            android:id="@+id/nav_host_fragment_activity_main"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:defaultNavHost="true"
            app:layout_constraintBottom_toTopOf="@id/bnvNavigation"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:navGraph="@navigation/mobile_navigation" />

        <ProgressBar
            android:id="@+id/progress"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:visibility="gone"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.1" />

</androidx.constraintlayout.widget.ConstraintLayout>
 */