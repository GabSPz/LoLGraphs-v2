package com.example.lolgraphs.ui.view.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.databinding.ItemResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.squareup.picasso.Picasso

class ChampViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemResultBinding.bind(view)

    fun render(champModel: ChampModel, onCLick: (ChampModel) -> Unit) {

        binding.tvHeroName.text = champModel.name
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.12.1/img/champion/${champModel.id}.png").into(binding.ivChampPhoto)
        itemView.setOnClickListener { onCLick(champModel) }
    }

}