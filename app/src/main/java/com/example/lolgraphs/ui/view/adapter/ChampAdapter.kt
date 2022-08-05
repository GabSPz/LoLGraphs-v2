package com.example.lolgraphs.ui.view.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.R
import com.example.lolgraphs.domain.model.ChampModel

class ChampAdapter(private val champList: Map<String, ChampModel>, private val onCLick: (ChampModel) -> Unit, /*private var onClickListener:(List<ChampionDc>) -> Unit*/):RecyclerView.Adapter<ChampViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChampViewHolder(layoutInflater.inflate(R.layout.activity_item_result, parent, false))
    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
        val item = champList.values.toTypedArray()[position]
        holder.render(item,onCLick)
    }

    override fun getItemCount(): Int = champList.keys.size
}


