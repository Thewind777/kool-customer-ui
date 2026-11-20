package com.kool.customer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kool.customer.data.model.StoreDetailsResponse
import com.kool.customer.data.repository.KoolRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel(private val companyId: Int) : ViewModel() {
    private val _details = MutableStateFlow<StoreDetailsResponse?>(null)
    val details: StateFlow<StoreDetailsResponse?> = _details

    init {
        viewModelScope.launch {
            _details.value = KoolRepository.getStoreDetails(companyId)
        }
    }

    class Factory(private val companyId: Int) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            RestaurantViewModel(companyId) as T
    }
}
