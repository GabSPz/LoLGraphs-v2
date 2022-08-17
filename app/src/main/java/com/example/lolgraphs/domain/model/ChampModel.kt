package com.example.lolgraphs.domain.model

import com.example.lolgraphs.data.db.entities.ChampEntity
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.data.model.subModel.ImageChamp
import com.example.lolgraphs.data.model.subModel.Info
import com.example.lolgraphs.data.model.subModel.Skins
import com.example.lolgraphs.domain.favoritemodel.ChampFavoriteModel
import com.example.lolgraphs.domain.model.submodel.*
import kotlinx.coroutines.flow.emptyFlow


data class ChampModel(
    //val version:String,
    val id:String,
    //val key:String,
    val name:String
    ////val title:String,
    ////val blurb:String,
    //val lore:String,
    //val info: InfoModel,
    //val image: ImageChampModel,
    //val skins:List<SkinsModel>?,
    //val enemyTips:List<String>?,
    //val tags:List<String>,
    //val partype:String,
    //val stats: StatsModel
)

fun ChampionDc.toDomain() = ChampModel(
    //version ,
    id,
    name
    //title,
    //blurb,
    //lore,
    //infoToDomain(info),
    //imageToDomain(image),
    //skinsToDomain(skins)  ,
    //enemytipsToDomain(enemyTips),
    //tags,
    //partype,
    //statsToDomain()

)
fun ChampEntity.toDomain() = ChampModel(
    name =  name,
    id = id
)

fun ChampModel.toDatabase() = ChampEntity(
    name = name,
    id = id
)
/*
fun ChampionDc.infoToDomain(info:Info) = InfoModel(
    info.attack,
    info.defense,
    info.magic,
    info.difficulty
)

fun ChampionDc.imageToDomain(image: ImageChamp) = ImageChampModel(
    image.imagePerChamp,
    image.sprite,
    image.group
)

fun ChampionDc.skinsToDomain(skinsList: List<Skins>?) :List<SkinsModel>  {
    return skinsList?.map { it.sskinsToDomain() } ?: emptyList()
}

     fun Skins.sskinsToDomain() = SkinsModel(
         this.idSkin,
         num,
         nameSkin
    )

fun ChampionDc.statsToDomain() = StatsModel(
    stats.hp,
    stats.mp,
    stats.moveSpeed,
    stats.armor,
    stats.crit,
    stats.attackDamage,
    stats.attackSpeed
)


fun ChampionDc.enemytipsToDomain(list : List<String>?):List<String>{
    return list ?: emptyList()
}




//champ entity


fun ChampEntity.toDomain() = ChampModel(
    version!!,
    champId!!,
    key!!,
    name!!,
)*/
