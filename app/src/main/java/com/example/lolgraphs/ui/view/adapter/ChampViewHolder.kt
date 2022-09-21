package com.example.lolgraphs.ui.view.adapter


import android.renderscript.ScriptGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.databinding.ActivityNavigationBinding
import com.example.lolgraphs.databinding.ItemResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.squareup.picasso.Picasso

sealed class ChampViewHolder(binding: ViewBinding):RecyclerView.ViewHolder(binding.root) {

    class ChampionResult(binding){

    }
    //private val binding = ItemResultBinding.bind(view)
//
    //fun render(champModel: ChampModel, onCLick: (ChampModel) -> Unit) {
//
    //    binding.tvHeroName.text = champModel.name
    //    Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.12.1/img/champion/${champModel.id}.png").into(binding.ivChampPhoto)
    //    itemView.setOnClickListener { onCLick(champModel) }
    //}

}