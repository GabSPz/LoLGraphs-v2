package com.example.lolgraphs.ui.view.adapter.adapterPerChamp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.R
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.data.model.subModel.Skins

class ChampSelectAdapter(private val champion: ChampionDc, private val skinsChamp: List<Skins>?, private val onClickListener: (Skins) -> Unit):RecyclerView.Adapter<ChampSelectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampSelectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChampSelectViewHolder(layoutInflater.inflate(R.layout.skins_result, parent,false))
    }

    override fun onBindViewHolder(holder: ChampSelectViewHolder, position: Int) {
        val  skin = skinsChamp!![position]
            holder.render(skin,champion,onClickListener)

    }

    override fun getItemCount(): Int = skinsChamp!!.size

}