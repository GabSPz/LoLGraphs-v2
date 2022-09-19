package com.example.lolgraphs.ui.view.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.R
import com.example.lolgraphs.core.extensions.HomeRecyclerViewItem
import com.example.lolgraphs.databinding.ItemMiniChampPlaceHolderBinding
import com.example.lolgraphs.databinding.ItemMiniChampResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import java.lang.IllegalArgumentException

class ChampAdapter(
    //private var champMap: Map<String, ChampModel>,
    //private var onCLick: (ChampModel) -> Unit
):RecyclerView.Adapter<ChampViewHolder>() {

    var items = listOf<HomeRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampViewHolder {
        return when(viewType){
            R.layout.item_mini_champ_place_holder -> ChampViewHolder.ChampionListLoading(
                ItemMiniChampPlaceHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_mini_champ_result -> ChampViewHolder.ChampionListResult(
                ItemMiniChampResultBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provider")
        }

    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
        when(holder){
            is ChampViewHolder.ChampionListLoading -> holder.bind()
            is ChampViewHolder.ChampionListResult -> holder.bind(items[position] as HomeRecyclerViewItem.ChampResult)
        }
        //val item = champMap.values.toTypedArray()[position]
        //holder.render(item,onCLick)
    }


    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is HomeRecyclerViewItem.ChampLoading -> R.layout.item_mini_champ_place_holder
            is HomeRecyclerViewItem.ChampResult -> R.layout.item_mini_champ_result
        }
    }
}


