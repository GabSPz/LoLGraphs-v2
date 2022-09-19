package com.example.lolgraphs.ui.view.adapter


import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lolgraphs.core.extensions.HomeRecyclerViewItem
import com.example.lolgraphs.databinding.ItemMiniChampPlaceHolderBinding
import com.example.lolgraphs.databinding.ItemMiniChampResultBinding
import com.squareup.picasso.Picasso

sealed class ChampViewHolder(binding: ViewBinding):RecyclerView.ViewHolder(binding.root){

    class ChampionListLoading(
        private val binding: ItemMiniChampPlaceHolderBinding
    ): ChampViewHolder(binding){
        fun bind(){}
    }

    class ChampionListResult(
        private val binding: ItemMiniChampResultBinding
    ): ChampViewHolder(binding){
        fun bind(champResult: HomeRecyclerViewItem.ChampResult){
            binding.tvHeroName.text = champResult.champModel.name
                Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.12.1/img/champion/${champResult.champModel.id}.png").into(binding.ivChampPhoto)
                itemView.setOnClickListener { champResult.onCLick(champResult.champModel) }
        }
    }

}

    //private val binding = ItemResultBinding.bind(view)
//
    //fun render(champModel: ChampModel, onCLick: (ChampModel) -> Unit) {
//
    //    binding.tvHeroName.text = champModel.name
    //    Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.12.1/img/champion/${champModel.id}.png").into(binding.ivChampPhoto)
    //    itemView.setOnClickListener { onCLick(champModel) }
    //}

