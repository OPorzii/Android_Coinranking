package com.piyawat.android_coinranking.model


import com.google.gson.annotations.SerializedName

data class CoinsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)