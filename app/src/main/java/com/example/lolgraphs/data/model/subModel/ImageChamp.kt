package com.example.lolgraphs.data.model.subModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageChamp(
    @SerializedName("full") var imagePerChamp:String,
    @SerializedName("sprite") var sprite:String,
    @SerializedName("group") var group:String,
    @SerializedName("x") var x:Int,
    @SerializedName("y") var y:Int,
    @SerializedName("w") var w:Int,
    @SerializedName("h") var h:Int,
): Serializable
