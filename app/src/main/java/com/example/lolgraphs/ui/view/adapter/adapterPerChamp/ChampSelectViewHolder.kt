package com.example.lolgraphs.ui.view.adapter.adapterPerChamp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.data.model.subModel.Skins
import com.example.lolgraphs.databinding.SkinsResultBinding
import com.squareup.picasso.Picasso

class ChampSelectViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private var binding = SkinsResultBinding.bind(view)

    fun render(skin: Skins, championModel: ChampionDc, onClickListener: (Skins) -> Unit) {

        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/${championModel.id}_${skin.num}.jpg").into(binding.ivSkinss)
        itemView.setOnClickListener { onClickListener(skin) }
    }
}