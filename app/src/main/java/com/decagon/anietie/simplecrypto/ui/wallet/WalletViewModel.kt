package com.decagon.anietie.simplecrypto.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.anietie.simplecrypto.model.SimpleCryptoRepository
import com.decagon.anietie.simplecrypto.model.domain.Cryptocurrency
import com.decagon.anietie.simplecrypto.util.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WalletViewModel constructor(
    private val simpleCryptoRepository: SimpleCryptoRepository
) : ViewModel() {

    private val _result: MutableLiveData<DataState<List<Cryptocurrency>>> = MutableLiveData()
    val result: LiveData<DataState<List<Cryptocurrency>>>
        get() = _result

    fun getPricesList(params: Map<String, String>) {
        viewModelScope.launch {
            try {
                simpleCryptoRepository.fetchPricesList(params).collect {
                    _result.value = it
                }
            } catch (e: Exception) {
                println("Something went wrong!")
            }
        }
    }
}