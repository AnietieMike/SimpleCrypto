package com.decagon.anietie.simplecrypto.model.remote.model

import com.decagon.anietie.simplecrypto.model.domain.Cryptocurrency
import com.decagon.anietie.simplecrypto.util.EntityMapper

class EthereumModelMapper : EntityMapper<Ethereum, Cryptocurrency> {
    override fun mapFromEntity(entity: Ethereum): Cryptocurrency {
        return Cryptocurrency(
            name = "Ethereum",
            usd = entity.usd,
            usd_24h_change = entity.usd_24h_change,
            usd_market_cap = entity.usd_market_cap
        )
    }
}
