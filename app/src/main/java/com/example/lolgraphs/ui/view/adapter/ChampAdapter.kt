package com.example.lolgraphs.ui.view.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.databinding.ItemPlaceHolderBinding
import com.example.lolgraphs.databinding.ItemResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.facebook.shimmer.ShimmerFrameLayout
import java.lang.IllegalArgumentException

class ChampAdapter(
    private var champMap: Map<String, ChampModel>,
    private var onCLick: (ChampModel) -> Unit
):RecyclerView.Adapter<ChampViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ChampViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType){
            1 -> ChampViewHolder.ChampionResult(
                ItemResultBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            2 -> ChampViewHolder.ChampionLoading(
                ItemPlaceHolderBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid View type")
        }
    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
        when(holder){
            is ChampViewHolder.ChampionResult -> holder.bind(champMap.values.toTypedArray()[position], onCLick)
            is ChampViewHolder.ChampionLoading -> holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return if (champMap.isNotEmpty()){
            champMap.size
        }else{
            30
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (champMap.isNotEmpty()){
            1
        }else{
            2
        }
    }
}



