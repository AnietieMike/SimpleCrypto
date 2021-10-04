package com.decagon.anietie.simplecrypto.model.remote

import com.decagon.anietie.simplecrypto.model.remote.model.CryptocurrencyListRemote
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("simple/price")
    suspend fun getCryptoPriceLists(
        @QueryMap params: Map<String, String>
    ): CryptocurrencyListRemote
}
