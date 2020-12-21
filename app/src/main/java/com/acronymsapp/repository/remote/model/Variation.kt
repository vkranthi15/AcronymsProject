package com.acronymsapp.repository.remote.model

import com.google.gson.annotations.SerializedName

data class Variation (
    @SerializedName("lf")
    val lf: String,
    @SerializedName("freq")
    val frequency: Int,
    val since: Int
)