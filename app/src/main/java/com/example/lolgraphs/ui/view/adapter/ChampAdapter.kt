package com.example.lolgraphs.ui.view.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.R
import com.example.lolgraphs.domain.model.ChampModel

class ChampAdapter(private var champMap: Map<String, ChampModel>, private var onCLick: (ChampModel) -> Unit):RecyclerView.Adapter<ChampViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChampViewHolder(layoutInflater.inflate(R.layout.item_result, parent, false))
    }

    override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
        val item = champMap.values.toTypedArray()[position]
        holder.render(item,onCLick)
    }

    override fun getItemCount(): Int = champMap.keys.size
}


