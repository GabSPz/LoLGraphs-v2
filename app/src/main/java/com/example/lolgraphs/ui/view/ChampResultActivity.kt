package com.example.lolgraphs.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.ui.view.adapter.adapterPerChamp.ChampSelectAdapter
import com.example.lolgraphs.databinding.ActivityChampResultBinding
import com.example.lolgraphs.data.model.subModel.Skins
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChampResultActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChampResultBinding
    private val champViewModel : ChampViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressChamp.isVisible = true
        binding.fbtnReturn.setOnClickListener { onBackPressed() }
        getDataChamp()

    }
    private fun getDataChamp(){
        val bundle = intent.extras
        val champData = bundle?.get("namechamp") as ChampionDc

        if (!champData.skins.isNullOrEmpty()){
            initRecycleView(champData)
        }else{
            callChamp(champData.id)
        }
    }

    private fun inputInfo(championDc: ChampionDc){
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championDc.id}_0.jpg").into(binding.ivSplashPhoto)
        binding.tVChampName.text= championDc.name
        binding.tvChampLore.text = championDc.lore
        if (championDc.enemyTips.size > 1){
            binding.tvEnemyTips.isVisible = true
            binding.tvEnemyTips1.text = championDc.enemyTips.first()
            binding.tvEnemyTips2.text = championDc.enemyTips.last()
        }
    }

    private fun initRecycleView(champModel: ChampionDc){
        binding.progressChamp.isVisible = false
        binding.tvSkins.isVisible = true
        binding.fbtnReturn.isVisible = true
        inputInfo(champModel)

        //binding champModel with the rvSkins
        binding.rvSkins.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.rvSkins.adapter =
            ChampSelectAdapter(champModel,champModel.skins){  onSkinSelected(it) }
    }

    private fun onSkinSelected(skin: Skins){
        Toast.makeText(this,skin.nameSkin,Toast.LENGTH_SHORT).show()
    }



     private fun callChamp(query:String){
         CoroutineScope(Dispatchers.IO).launch {
             champViewModel.onCreate("/$query.")

             runOnUiThread{
                 champViewModel.champModel.observe(this@ChampResultActivity, Observer {
                     val result = it
                     if (!result?.onlyChamp.isNullOrEmpty()){
                         val champMap = result?.onlyChamp?.toMutableMap() ?: emptyMap()
                         val champModel = champMap.values.toTypedArray()[0]
                         initRecycleView(champModel)
                     }
                 })
             }
         }
    }
}
