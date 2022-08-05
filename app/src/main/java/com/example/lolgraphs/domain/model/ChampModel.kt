package com.example.lolgraphs.domain.model

import com.example.lolgraphs.data.database.entities.ChampEntity
import com.example.lolgraphs.data.model.ChampionDc
import com.example.lolgraphs.data.model.subModel.EnemyTips
import com.example.lolgraphs.data.model.subModel.Skins
import com.example.lolgraphs.data.model.subModel.Tags
import com.example.lolgraphs.domain.model.submodel.*


data class ChampModel(
    val version:String,
    val id:String,
    val key:String,
    val name:String,
    val title:String,
    val blurb:String,
    val lore:String,
    val info: InfoModel,
    val image: ImageChampModel,
    val skins:List<SkinsModel>,
    val enemyTips:List<EnemyTipsModel>,
    val tags:List<TagsModel>,
    val partype:String,
    val stats: StatsModel,
)

fun ChampionDc.toDomain() = ChampModel(
    version,
    id,
    key,
    name,
    title,
    blurb,
    lore,
    infoToDomain(),
    imageToDomain(),
    skinsToDomain(),
    enemyTipsToDomain(),
    tagsToDomain(),
    partype,
    statsToDomain()
)

fun ChampionDc.infoToDomain() = InfoModel(
    info.attack,
    info.defense,
    info.magic,
    info.difficulty

)

fun ChampionDc.imageToDomain() = ImageChampModel(
    image.imagePerChamp,
    image.sprite,
    image.group
)

fun ChampionDc.skinsToDomain() :List<SkinsModel> = skins.map { it.skinsToDomain() }

    private fun Skins.skinsToDomain() = SkinsModel(
        idSkin,
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

fun ChampionDc.enemyTipsToDomain(): List<EnemyTipsModel> = enemyTips.map { it.changeEnemyTips() }

    private fun EnemyTips.changeEnemyTips() = EnemyTipsModel(tip)

fun ChampionDc.tagsToDomain():List<TagsModel> = tags.map { it.changeTags() }

    private fun Tags.changeTags() = TagsModel(tag)

//champ entity

/*
fun ChampEntity.toDomain() = ChampModel(
    version!!,
    champId!!,
    key!!,
    name!!,
)*/
