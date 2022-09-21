package com.example.lolgraphs.core.extensions

import com.example.lolgraphs.domain.model.ChampModel

sealed class HomeRecyclerViewItem{
    class ChampResult(
        val champModel: ChampModel,
        val onCLick: (ChampModel) -> Unit
    ):HomeRecyclerViewItem()

    class ChampLoading(
    ): HomeRecyclerViewItem()
}