package com.example.lolgraphs.data.model

import com.example.lolgraphs.data.model.subModel.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.inject.Singleton


data class ChampionDc(
    @SerializedName("version") var version:String,
    @SerializedName("id") var id:String,
    @SerializedName("key") var key:String,
    @SerializedName("name") var name:String,
    @SerializedName("tittle") var title:String,
    @SerializedName("blurb") var blurb:String,
    @SerializedName("lore") var lore:String,
    @SerializedName("info") var info: Info,
    @SerializedName("image") var image: ImageChamp,
    @SerializedName("skins") var skins:List<Skins>,
    @SerializedName("enemytips") var enemyTips:List<EnemyTips>,
    @SerializedName("tags") var tags:List<Tags>,
    @SerializedName("partype") var partype:String,
    @SerializedName("stats") var stats: Stats,
) : Serializable {

}
