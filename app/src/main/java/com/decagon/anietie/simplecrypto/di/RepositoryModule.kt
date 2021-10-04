package com.decagon.anietie.simplecrypto.di

import com.decagon.anietie.simplecrypto.model.SimpleCryptoRepository
import com.decagon.anietie.simplecrypto.model.remote.model.BitcoinModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.DashModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.DogecoinModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.EthereumModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.LitecoinModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.RippleModelMapper
import org.koin.dsl.module

val repositoryModule = module {

    factory { BitcoinModelMapper() }
    factory { EthereumModelMapper() }
    factory { RippleModelMapper() }
    factory { LitecoinModelMapper() }
    factory { DogecoinModelMapper() }
    factory { DashModelMapper() }

    single {
        SimpleCryptoRepository(
            apiService = get(),
            bitcoinModelMapper = get(),
            ethereumModelMapper = get(),
            rippleModelMapper = get(),
            litecoinModelMapper = get(),
            dogecoinModelMapper = get(),
            dashModelMapper = get()
        )
    }
}
