package com.example.lolgraphs.data.model.subModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Stats(
    @SerializedName("hp") var hp: Float,
    @SerializedName("hpperlevel") var hpPerLevel: Float,
    @SerializedName("mp") var mp: Float,
    @SerializedName("mpperlevel") var mpPerLevel: Float,
    @SerializedName("movespeed")var moveSpeed: Float,
    @SerializedName("armor")var armor: Float,
    @SerializedName("armorperlevel") var armorPerLevel: Float,
    @SerializedName("spellblock") var spellBlock: Float,
    @SerializedName("spellblockperlevel") var spellBlockPerLevel: Float,
    @SerializedName("attackrange") var attackRange: Float,
    @SerializedName("hpregen") var hpRegen: Float,
    @SerializedName("hpregenperlevel") var hpRegenPerLevel: Float,
    @SerializedName("mpregen") var mpRegen: Float,
    @SerializedName("mpregenperlevel") var mpRegenPerLevel: Float,
    @SerializedName("crit") var crit: Float,
    @SerializedName("critperlevel") var citPerLevel: Float,
    @SerializedName("attackdamage") var attackDamage: Float,
    @SerializedName("attackdamageperlevel") var attackDamagePerLevel: Float,
    @SerializedName("attackspeedperlevel") var attackSpeedPerLevel: Float,
    @SerializedName("attackspeed")var attackSpeed: Float
): Serializable
