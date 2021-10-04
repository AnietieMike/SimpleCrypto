package com.decagon.anietie.simplecrypto.model.remote.model

import com.decagon.anietie.simplecrypto.model.domain.Cryptocurrency
import com.decagon.anietie.simplecrypto.util.EntityMapper

class BitcoinModelMapper : EntityMapper<Bitcoin, Cryptocurrency> {
    override fun mapFromEntity(entity: Bitcoin): Cryptocurrency {
        return Cryptocurrency(
            name = "Bitcoin",
            usd = entity.usd,
            usd_24h_change = entity.usd_24h_change,
            usd_market_cap = entity.usd_market_cap
        )
    }
}
