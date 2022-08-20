package com.example.lolgraphs.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.data.model.subModel.Skins
import com.example.lolgraphs.ui.view.adapter.adapterPerChamp.ChampSelectAdapter
import com.example.lolgraphs.databinding.ActivityChampResultBinding
import com.example.lolgraphs.databinding.FragmentFavoritesBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.toDomain
import com.example.lolgraphs.ui.viewModel.ChampViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

@AndroidEntryPoint
class ChampResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChampResultBinding
    private val championViewModel : ChampViewModel by viewModels()
    //val Context.dataStore by preferencesDataStore("USER_DS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        championViewModel.isLoading.observe(this@ChampResultActivity, Observer {
            binding.progressChamp.isVisible = it
            binding.tvSkins.isVisible = !it
            binding.cvFavorite.isVisible = !it
        })
        getDataChamp()

    }
    private fun getDataChamp(){
        val bundle = intent.extras
        val champData = bundle?.get("namechamp") as String
        callChamp(champData)
    }

    private fun inputInfo(championDc: ChampionDc){
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championDc.id}_0.jpg").into(binding.ivSplashPhoto)
        binding.tVChampName.text= championDc.name
        binding.tvChampLore.text = championDc.lore

        binding.cvFavorite.setOnClickListener { checkFavorite(championDc.toDomain()) }
        championViewModel.champFav.observe(this, Observer {
            if (!it[championDc.name]?.name.isNullOrEmpty()){
                binding.cvFavorite.isChecked = true
            }
        })
        if (championDc.enemyTips?.size!! > 1){
            binding.tvEnemyTips.isVisible = true
            binding.tvEnemyTips1.text = championDc.enemyTips!!.first().toString()
            binding.tvEnemyTips2.text = championDc.enemyTips!!.last().toString()
        }
    }

    private fun initRecycleView(champModel: ChampionDc){
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
            championViewModel.onSearch(query)

            runOnUiThread{
                championViewModel.champDc.observe(this@ChampResultActivity, Observer {
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

    private fun checkFavorite(champModel: ChampModel){

        if (binding.cvFavorite.isChecked){
            championViewModel.onFavoriteChamp(true, champModel)
            //sendChampion(champModel)
            //championViewModel.champFav.observe(this@ChampResultActivity, Observer {
            //    it.toMutableMap().put(champModel.name,champModel)
            //})
            Toast.makeText(this,"Guardando en Favoritos",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Eliminando de Favoritos",Toast.LENGTH_SHORT).show()
        }
    }

   // private fun champCheck (champModel: ChampModel){
   //     championViewModel.champFav.observe(this, Observer {
   //         val checkChamp = it[champModel.name]
   //         if (checkChamp?.name.isNullOrEmpty()){
//
   //         }else{
   //             binding.cvFavorite.isActivated = true
   //         }
   //     })
   // }

    private fun sendChampion (champModel: ChampModel){
        Intent(this, FragmentFavoritesBinding::class.java).apply {
            putExtra("allChamp",champModel as Serializable)
        }
    }
}
