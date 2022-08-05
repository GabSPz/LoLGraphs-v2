package com.example.lolgraphs.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.ui.view.adapter.adapterPerChamp.ChampSelectAdapter
import com.example.lolgraphs.databinding.ActivityChampResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.submodel.SkinsModel
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChampResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChampResultBinding
    private val championViewModel : ChampViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        championViewModel.isLoading.observe(this@ChampResultActivity, Observer {
            binding.progressChamp.isVisible = it
            binding.tvSkins.isVisible = !it

        })
        getDataChamp()
    }
    private fun getDataChamp(){
        val bundle = intent.extras
        val champData = bundle?.get("namechamp") as String
        callChamp(champData)
    }

    private fun inputInfo(championDc: ChampModel){
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championDc.id}_0.jpg").into(binding.ivSplashPhoto)
        binding.tVChampName.text= championDc.name
        binding.tvChampLore.text = championDc.lore

        if (championDc.enemyTips.size > 1){
            binding.tvEnemyTips.isVisible = true
            binding.tvEnemyTips1.text = championDc.enemyTips.first().toString()
            binding.tvEnemyTips2.text = championDc.enemyTips.last().toString()
        }
    }

    private fun initRecycleView(champModel: ChampModel){
        inputInfo(champModel)
        //binding champModel with the rvSkins
        binding.rvSkins.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.rvSkins.adapter =
            ChampSelectAdapter(champModel,champModel.skins){  onSkinSelected(it) }
    }

    private fun onSkinSelected(skin: SkinsModel){
        Toast.makeText(this,skin.nameSkin,Toast.LENGTH_SHORT).show()
    }

    private fun callChamp(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            championViewModel.onSearch(query)

            runOnUiThread{
                championViewModel.champModel.observe(this@ChampResultActivity, Observer {
                    val result = it
                    if (!result.isNullOrEmpty()){
                        val champMap = result.toMutableMap()
                        val champModel = champMap.values.toTypedArray()[0]
                        initRecycleView(champModel)
                    }
                })
            }
         }
    }
}
