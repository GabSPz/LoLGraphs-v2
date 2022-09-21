package com.example.lolgraphs.ui.view.adapter



import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lolgraphs.databinding.ItemPlaceHolderBinding
import com.example.lolgraphs.databinding.ItemResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.squareup.picasso.Picasso

sealed class ChampViewHolder(binding: ViewBinding):RecyclerView.ViewHolder(binding.root) {

    class ChampionResult(private val binding: ItemResultBinding): ChampViewHolder(binding){
        fun bind(champModel: ChampModel, onCLick: (ChampModel) -> Unit){
            binding.tvHeroName.text = champModel.name
            Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/12.12.1/img/champion/${champModel.id}.png").into(binding.ivChampPhoto)
            itemView.setOnClickListener { onCLick(champModel) }
        }
    }

    class ChampionLoading(private val binding: ItemPlaceHolderBinding): ChampViewHolder(binding){
        fun bind() {
            binding.shimmerContainer.startShimmer()
        }
    }

}