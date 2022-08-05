package com.example.lolgraphs.domain.model.submodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//@Entity
data class StatsModel  constructor(
    //@ColumnInfo(name = "hp")
    val hp: Float,
    //@ColumnInfo(name = "hpperlevel") var hpPerLevel: Float,
    //@ColumnInfo(name = "mp")
    val mp: Float,
    //@ColumnInfo(name = "mpperlevel") var mpPerLevel: Float,
    //@ColumnInfo(name = "movespeed")
    val moveSpeed: Float,
    //@ColumnInfo(name = "armor")
    val armor: Float,
    //@ColumnInfo(name = "armorperlevel") var armorPerLevel: Float,
    //@ColumnInfo(name = "spellblock") var spellBlock: Float,
    //@ColumnInfo(name = "spellblockperlevel") var spellBlockPerLevel: Float,
    //@ColumnInfo(name = "attackrange") var attackRange: Float,
    //@ColumnInfo(name = "hpregen") var hpRegen: Float,
    //@ColumnInfo(name = "hpregenperlevel") var hpRegenPerLevel: Float,
    //@ColumnInfo(name = "mpregen") var mpRegen: Float,
    //@ColumnInfo(name = "mpregenperlevel") var mpRegenPerLevel: Float,
    //@ColumnInfo(name = "crit")
    val crit: Float,
    //@ColumnInfo(name = "critperlevel") var citPerLevel: Float,
    //@ColumnInfo(name = "attackdamage")
    val attackDamage: Float,
    //@ColumnInfo(name = "attackdamageperlevel") var attackDamagePerLevel: Float,
   // @ColumnInfo(name = "attackspeedperlevel") var attackSpeedPerLevel: Float,
    //@ColumnInfo(name = "attackspeed")
    val attackSpeed: Float
)
