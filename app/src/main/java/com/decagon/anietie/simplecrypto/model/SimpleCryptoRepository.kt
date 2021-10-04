package com.decagon.anietie.simplecrypto.model

import com.decagon.anietie.simplecrypto.model.domain.Cryptocurrency
import com.decagon.anietie.simplecrypto.model.remote.ApiService
import com.decagon.anietie.simplecrypto.model.remote.model.BitcoinModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.DashModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.DogecoinModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.EthereumModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.LitecoinModelMapper
import com.decagon.anietie.simplecrypto.model.remote.model.RippleModelMapper
import com.decagon.anietie.simplecrypto.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SimpleCryptoRepository constructor(
    private val apiService: ApiService,
    private val bitcoinModelMapper: BitcoinModelMapper,
    private val ethereumModelMapper: EthereumModelMapper,
    private val rippleModelMapper: RippleModelMapper,
    private val litecoinModelMapper: LitecoinModelMapper,
    private val dogecoinModelMapper: DogecoinModelMapper,
    private val dashModelMapper: DashModelMapper
) {

    suspend fun fetchPricesList(
        params: Map<String, String>
    ): Flow<DataState<List<Cryptocurrency>>> = flow {
        emit(DataState.loading())
        try {
            val pricesList = apiService.getCryptoPriceLists(params)
            val bitcoin = bitcoinModelMapper.mapFromEntity(pricesList.bitcoin)
            val ethereum = ethereumModelMapper.mapFromEntity(pricesList.ethereum)
            val xrp = rippleModelMapper.mapFromEntity(pricesList.ripple)
            val litecoin = litecoinModelMapper.mapFromEntity(pricesList.litecoin)
            val dogecoin = dogecoinModelMapper.mapFromEntity(pricesList.dogecoin)
            val dash = dashModelMapper.mapFromEntity(pricesList.dash)

            val cryptoList = listOf(bitcoin, ethereum, xrp, dogecoin, litecoin, dash)
            emit(DataState.success(cryptoList))
        } catch (e: Exception) {
            emit(DataState.error<Nothing>("Error", e))
        }
    }
}
