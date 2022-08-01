package com.example.lolgraphs.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener    {

    private lateinit var binding : ActivityMainBinding
    private val champViewModel : ChampViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progress.isVisible = true
        callServiceGetUsers()
        binding.svChamps.setOnQueryTextListener(this)

    }



    private fun callServiceGetUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            champViewModel.onCreate(".")
            runOnUiThread {
                champViewModel.champModel.observe(this@MainActivity, Observer {
                    val allChamps = it?.onlyChamp?.toMutableMap() ?: emptyMap()
                    initRecycleView(allChamps)
                    binding.recycleChamps.isVisible = true
                    binding.svChamps.isVisible = true
                    binding.progress.isVisible = false
                })
            }
        }
        /*val userService: APIService = getAllChamps().create(APIService::class.java)
        val result: Call<ChampResponse> = userService.getAllChamp()

        result.enqueue(object: Callback<ChampResponse> {
            override fun onResponse(
                call: Call<ChampResponse>,
                response: Response<ChampResponse>
            ) {
                val allChamps =  response.body()?.champions?.toMutableMap() ?: emptyMap()
                initRecycleView(allChamps)
                binding.recycleChamps.isVisible = true
                binding.svChamps.isVisible = true
                binding.progress.isVisible = false

            }

            override fun onFailure(call: Call<ChampResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
            }
        })
        */
    }

    private fun initRecycleView(mapChamps: Map<String, ChampionDc>){
        binding.recycleChamps.layoutManager = LinearLayoutManager(this)
        binding.recycleChamps.adapter =
            ChampAdapter(mapChamps){champion -> onItemSelected(champion)}
    }

    private fun onItemSelected(champion: ChampionDc){
        //go to champ result
        val intent = Intent(this, ChampResultActivity::class.java).apply {
            putExtra("namechamp",champion)
        }
        startActivity(intent)
    }

    private fun showError(){
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    //When the user use the search view, we coding the backend


    private fun searchChampionById(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            champViewModel.onCreate("/$query.")

            runOnUiThread {
                champViewModel.champModel.observe(this@MainActivity, Observer {
                    val response = it
                    val champModel = response?.onlyChamp?.toMutableMap() ?: emptyMap()
                    initRecycleView(champModel)

                    hideKeyBoard()
                })
            }
        }
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
                binding.progress.isVisible = true
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



