package com.acronymsapp.repository.remote.model

import com.google.gson.annotations.SerializedName

data class Acromine (
    @SerializedName("sf")
    val shortForm: String,
    @SerializedName("lfs")
    val longForms: List<LongForm>
)