package com.piyawat.android_coinranking.model


import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("sign")
    val sign: String,
    @SerializedName("symbol")
    val symbol: String
)