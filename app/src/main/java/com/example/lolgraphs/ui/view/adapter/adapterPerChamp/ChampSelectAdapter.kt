package com.example.lolgraphs.ui.view.adapter.adapterPerChamp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.R
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.submodel.SkinsModel

class ChampSelectAdapter(private val champion: ChampModel, private val skinChamp: List<SkinsModel>, private val onClickListener: (SkinsModel) -> Unit):RecyclerView.Adapter<ChampSelectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampSelectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChampSelectViewHolder(layoutInflater.inflate(R.layout.skins_result, parent,false))
    }

    override fun onBindViewHolder(holder: ChampSelectViewHolder, position: Int) {
        val item = skinChamp[position]
        holder.render(item,champion,onClickListener)
    }

    override fun getItemCount(): Int = skinChamp.size

}