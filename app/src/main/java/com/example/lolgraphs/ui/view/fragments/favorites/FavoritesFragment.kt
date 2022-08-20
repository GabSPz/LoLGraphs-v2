package com.example.lolgraphs.ui.view.fragments.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolgraphs.databinding.FragmentFavoritesBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.ui.view.ChampResultActivity
import com.example.lolgraphs.ui.view.adapter.ChampAdapter
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var champViewModel: ChampViewModel

    private val championMap = mutableMapOf<String, ChampModel>()
    private lateinit var adapter: ChampAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        champViewModel =
            ViewModelProvider(this).get(ChampViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        champViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.pbFavorite.isVisible = it
        })
        getFavoriteChamp()
        return root
    }
    private fun initRecycleView(map: Map<String,ChampModel>){
        adapter = ChampAdapter(map){champion -> onItemSelected(champion)}
        binding.rvFavorites.layoutManager = LinearLayoutManager(this.context)
        binding.rvFavorites.adapter = adapter

    }

    private fun onItemSelected(champion: ChampModel){
        //go to champ result
        val intent = Intent(this.context, ChampResultActivity::class.java).apply {
            putExtra("namechamp",champion.name)
        }
        startActivity(intent)
    }

    private fun showText(){
        binding.tvNoFavorites.isVisible = true
    }

    private fun getFavoriteChamp (){
        CoroutineScope(Dispatchers.IO).launch {
            champViewModel.getFavoriteChamp()

            activity?.runOnUiThread {
                champViewModel.champFav.observe(viewLifecycleOwner, Observer {
                    val champs = it?.toMutableMap() ?: emptyMap()
                    if (champs.isNotEmpty()) {
                        championMap.clear()
                        championMap.putAll(champs)
                        initRecycleView(championMap)
                        adapter.notifyDataSetChanged()
                    }else{
                        showText()
                    }
                })
            }

        }
        //if (championMap.isEmpty()){
        //    binding.rvFavorites.isVisible = false
        //    showText()
        //}else{
        //    initRecycleView(championMap)
        //    binding.rvFavorites.isVisible = true
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}