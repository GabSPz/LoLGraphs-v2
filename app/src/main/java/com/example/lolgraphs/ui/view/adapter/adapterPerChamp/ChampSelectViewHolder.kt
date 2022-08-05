package com.example.lolgraphs.ui.view.adapter.adapterPerChamp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lolgraphs.databinding.SkinsResultBinding
import com.example.lolgraphs.domain.model.ChampModel
import com.example.lolgraphs.domain.model.submodel.SkinsModel
import com.squareup.picasso.Picasso

class ChampSelectViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private var binding = SkinsResultBinding.bind(view)

    fun render(item: SkinsModel, championModel: ChampModel, onClickListener: (SkinsModel) -> Unit) {

        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/img/champion/loading/${championModel.id}_${item.num}.jpg").into(binding.ivSkinss)
        itemView.setOnClickListener { onClickListener(item) }
    }
}