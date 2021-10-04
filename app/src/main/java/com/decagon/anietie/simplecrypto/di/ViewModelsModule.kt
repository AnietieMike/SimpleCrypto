package com.decagon.anietie.simplecrypto.di

import com.decagon.anietie.simplecrypto.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
}