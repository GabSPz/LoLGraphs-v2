package com.example.lolgraphs.ui.view.fragments.home

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolgraphs.databinding.FragmentHomeBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.ui.view.ChampResultActivity
import com.example.lolgraphs.ui.view.adapter.ChampAdapter
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var champViewModel: ChampViewModel

    private val championMap = mutableMapOf<String, ChampModel>()
    private lateinit var adapter: ChampAdapter
    private val binding get() = _binding!!

    //These last two vars are help me for searchView when onQueryTextChange
    private var champSize : Int = 0
    private val championMapFilter =  mutableMapOf<String, ChampModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        champViewModel =
           ViewModelProvider(this).get(ChampViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.svChamps.setOnQueryTextListener(this)
        champViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
            binding.recycleChamps.isVisible = !it
            binding.svChamps.isVisible = !it
        })
        callServiceGetUsers()
        return root
    }

    private fun callServiceGetUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            champViewModel.onCreate()

            activity?.runOnUiThread {
                champViewModel.champModel.observe(
                    viewLifecycleOwner, Observer {
                        val champs = it?.toMutableMap() ?: emptyMap()
                        championMap.clear()
                        championMap.putAll(champs)
                        initRecycleView(championMap)
                        adapter.notifyDataSetChanged()

                        champSize = champs.keys.size
                        championMapFilter.putAll(champs)
                    }
                )
            }
        }
    }

    private fun initRecycleView(map: Map<String,ChampModel>){
        adapter = ChampAdapter(map){champion -> onItemSelected(champion)}
        binding.recycleChamps.layoutManager = LinearLayoutManager(this@HomeFragment.context)
        binding.recycleChamps.adapter = adapter

        }

    private fun onItemSelected(champion: ChampModel){
        //go to champ result
        val intent = Intent(this.context, ChampResultActivity::class.java).apply {
            putExtra("NAME_CHAMP",champion.name)
        }
        startActivity(intent)
    }

    private fun showError(){
        Toast.makeText(this@HomeFragment.context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    //When the user use the search view, we coding the backend

    private fun searchChampionById(query: String){
        val champModel = championMap[query]
        if (!champModel?.name.isNullOrEmpty()) {
            championMap.apply {
                clear()
                put(query, champModel!!)
            }
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
            val finalQuery = query.lowercase().replaceFirstChar { upperQuery }
            searchChampionById(finalQuery)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()){
            if (binding.recycleChamps.size < champSize) {
                championMap.clear()
                championMap.putAll(championMapFilter)
                initRecycleView(championMap)
                adapter.notifyDataSetChanged()
            }
        }else{
            val searchText = newText.lowercase(Locale.getDefault())
            championMap.clear()
            println(championMapFilter)
            championMapFilter.values.forEach {
                if (it.name.lowercase(Locale.getDefault()).contains(searchText)){
                    championMap[it.name] = it
                }
            }
            initRecycleView(championMap)
            adapter.notifyDataSetChanged()
        }
        return false
    }

    private fun hideKeyBoard(){
        val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.container.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



