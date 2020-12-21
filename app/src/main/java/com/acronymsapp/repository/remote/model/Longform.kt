package com.acronymsapp.repository.remote.model

import com.google.gson.annotations.SerializedName

data class LongForm (
    @SerializedName("lf")
    val longform: String,
    @SerializedName("freq")
    val frequency: Int,
    val since: Int,
    @SerializedName("vars")
    val variations: List<Variation>
)