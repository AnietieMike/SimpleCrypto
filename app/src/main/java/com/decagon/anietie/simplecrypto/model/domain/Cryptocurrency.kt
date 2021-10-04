package com.decagon.anietie.simplecrypto.model.domain

data class Cryptocurrency(
    val name: String,
    val usd: Double,
    val usd_24h_change: Double,
    val usd_market_cap: Double
)
