package com.example.lolgraphs.data.model.subModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Info(
    @SerializedName("attack") var attack:Int,
    @SerializedName("defense") var defense:Int,
    @SerializedName("magic") var magic:Int,
    @SerializedName("difficulty") var difficulty:Int
): Serializable

