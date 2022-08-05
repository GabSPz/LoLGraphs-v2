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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.ui.view.adapter.ChampAdapter
import com.example.lolgraphs.databinding.ActivityMainBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener    {

    private lateinit var binding : ActivityMainBinding
    private val champViewModel : ChampViewModel by viewModels()

    private val championMap = mutableMapOf<String,ChampModel>()
    private lateinit var adapter: ChampAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        champViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
        callServiceGetUsers()
        binding.svChamps.setOnQueryTextListener(this)

    }



    private fun callServiceGetUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            champViewModel.onCreate()
            runOnUiThread {
                champViewModel.champModel.observe(this@MainActivity, Observer {
                    val prev = it?.toMutableMap() ?: emptyMap()
                    championMap.clear()
                    championMap.putAll(prev)
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



