package com.riocallos.printreceipt.di

import com.riocallos.printreceipt.ui.receipt.ReceiptViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * View model module for containing repositories and view models
 */
val viewModelModule = module {

    viewModel { ReceiptViewModel() }

}