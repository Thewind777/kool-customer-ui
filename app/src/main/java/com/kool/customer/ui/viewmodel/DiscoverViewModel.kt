package com.kool.customer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kool.customer.data.model.RestaurantData
import com.kool.customer.data.repository.KoolRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiscoverViewModel : ViewModel() {
    private val _restaurants = MutableStateFlow<List<RestaurantData>>(emptyList())
    val restaurants: StateFlow<List<RestaurantData>> = _restaurants

    init {
        viewModelScope.launch {
            _restaurants.value = KoolRepository.getRestaurants()
        }
    }
}
