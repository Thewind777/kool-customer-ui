package com.kool.customer.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.kool.customer.data.api.KoolApiClient.BASE_URL

// ---- Restaurants list ----

@JsonClass(generateAdapter = true)
data class RestaurantsResponse(
    @Json(name = "Result") val result: String? = null,
    @Json(name = "Data") val data: List<RestaurantData>? = null,
)

@JsonClass(generateAdapter = true)
data class RestaurantData(
    @Json(name = "iCompanyId") val id: String? = null,
    @Json(name = "vCompanyName") val name: String? = null,
    @Json(name = "vImage") val imageRelative: String? = null,
    @Json(name = "vAvgRating") val rating: String? = null,
    @Json(name = "iTotalRating") val reviewCount: String? = null,
    @Json(name = "vDeliveryTime") val deliveryTime: String? = null,
    @Json(name = "vDeliveryFee") val deliveryFee: String? = null,
) {
    val imageUrl: String?
        get() = imageRelative?.takeIf { it.isNotBlank() }?.let { BASE_URL + it }
}

// ---- Store details / menu ----

@JsonClass(generateAdapter = true)
data class StoreDetailsResponse(
    @Json(name = "Result") val result: String? = null,
    @Json(name = "CompanyInfo") val companyInfo: CompanyInfo? = null,
    @Json(name = "MenuItems") val menuItems: List<MenuItemData>? = null,
)

@JsonClass(generateAdapter = true)
data class CompanyInfo(
    @Json(name = "vCompanyName") val name: String? = null,
    @Json(name = "vImage") val imageRelative: String? = null,
    @Json(name = "vLogo") val logoRelative: String? = null,
) {
    val imageUrl: String?
        get() = imageRelative?.takeIf { it.isNotBlank() }?.let { BASE_URL + it }
    val logoUrl: String?
        get() = logoRelative?.takeIf { it.isNotBlank() }?.let { BASE_URL + it }
            ?: imageUrl
}

@JsonClass(generateAdapter = true)
data class MenuItemData(
    @Json(name = "iMenuItemId") val id: String? = null,
    @Json(name = "vItemName") val name: String? = null,
    @Json(name = "vItemDescription") val description: String? = null,
    @Json(name = "vPrice") val price: String? = null,
    @Json(name = "vImage") val imageRelative: String? = null,
    @Json(name = "iMostOrder") val mostOrdered: String? = null,
) {
    val imageUrl: String?
        get() = imageRelative?.takeIf { it.isNotBlank() }?.let { BASE_URL + it }
}
