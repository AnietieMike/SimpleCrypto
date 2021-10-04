package com.decagon.anietie.simplecrypto.model.domain

import com.google.gson.annotations.SerializedName

data class CryptocurrencyList(
    @SerializedName("crypto")
    val crypto: Cryptocurrency
)
