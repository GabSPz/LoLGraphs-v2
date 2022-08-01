package com.example.lolgraphs.data.model.subModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Skins(
    @SerializedName("id") var idSkin: String,
    @SerializedName("num") var num:Int,
    @SerializedName("name") var nameSkin:String
): Serializable
