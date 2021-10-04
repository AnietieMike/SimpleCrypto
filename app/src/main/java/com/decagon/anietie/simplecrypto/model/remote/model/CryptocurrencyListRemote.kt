package com.decagon.anietie.simplecrypto.model.remote.model

data class CryptocurrencyListRemote(
    val bitcoin: Bitcoin,
    val dash: Dash,
    val dogecoin: Dogecoin,
    val ethereum: Ethereum,
    val litecoin: Litecoin,
    val ripple: Ripple
)
