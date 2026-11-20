package com.kool.customer.data.repository

import com.kool.customer.data.api.KoolApiClient
import com.kool.customer.data.model.RestaurantData
import com.kool.customer.data.model.StoreDetailsResponse

object KoolRepository {
    private val api = KoolApiClient.api

    suspend fun getRestaurants(): List<RestaurantData> {
        return try {
            api.loadAvailableRestaurants().data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getStoreDetails(companyId: Int): StoreDetailsResponse? {
        return try {
            api.getStoreDetails(companyId = companyId)
        } catch (e: Exception) {
            null
        }
    }
}
