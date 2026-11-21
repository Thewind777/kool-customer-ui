package com.kool.customer.data.model

import com.kool.customer.data.api.KoolApiClient
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The live Kool API is inconsistent about image paths: store and menu-item
 * records return absolute URLs, other records return paths relative to the
 * site root. Normalise both forms into an absolute URL.
 */
private fun absoluteUrl(path: String?): String? {
    val value = path?.trim()?.takeIf { it.isNotEmpty() } ?: return null
    if (value.startsWith("http://") || value.startsWith("https://")) return value
    val base = KoolApiClient.BASE_URL.trimEnd('/')
    return if (value.startsWith("/")) base + value else "$base/$value"
}

// ---- Restaurants list ----
// Live payload: {"Action":"1","message":[{ ...store... }]}
// `message` is a plain string (e.g. "LBL_NO_RESTAURANT_FOUND_TXT") when the
// call fails, which the repository treats as an empty result.

@JsonClass(generateAdapter = true)
data class RestaurantsResponse(
    @Json(name = "Action") val action: String? = null,
    @Json(name = "message") val stores: List<RestaurantData>? = null,
)

@JsonClass(generateAdapter = true)
data class RestaurantData(
    @Json(name = "iCompanyId") val id: String? = null,
    @Json(name = "vName") val name: String? = null,
    @Json(name = "vImage") val imagePath: String? = null,
    @Json(name = "vAvgRating") val rating: String? = null,
    @Json(name = "iTotalRating") val reviewCount: String? = null,
    @Json(name = "Restaurant_OrderPrepareTime") val deliveryTime: String? = null,
    @Json(name = "vDeliveryFee") val deliveryFee: String? = null,
) {
    val imageUrl: String?
        get() = absoluteUrl(imagePath)
}

// ---- Store details / menu ----
// Live payload: {"Action":"1","message":{ ...store..., "CompanyDetails":{
//   "CompanyFoodData":[{"vMenu":"بيتزا","menu_items":[{ ...item... }]}]}}}

@JsonClass(generateAdapter = true)
data class StoreDetailsResponse(
    @Json(name = "Action") val action: String? = null,
    @Json(name = "message") val store: StoreDetails? = null,
) {
    val companyInfo: CompanyInfo?
        get() = store?.let {
            CompanyInfo(
                name = it.name,
                rating = it.rating,
                imagePath = it.coverImage ?: it.imagePath,
                logoPath = it.imagePath,
            )
        }

    /** Flattens every food menu into the single list the menu screen renders. */
    val menuItems: List<MenuItemData>?
        get() = store?.companyDetails?.foodMenus?.flatMap { it.items.orEmpty() }
}

@JsonClass(generateAdapter = true)
data class StoreDetails(
    @Json(name = "vName") val name: String? = null,
    @Json(name = "vAvgRating") val rating: String? = null,
    @Json(name = "vImage") val imagePath: String? = null,
    @Json(name = "vCoverImage") val coverImage: String? = null,
    @Json(name = "CompanyDetails") val companyDetails: StoreCompanyDetails? = null,
)

@JsonClass(generateAdapter = true)
data class StoreCompanyDetails(
    @Json(name = "CompanyFoodData") val foodMenus: List<FoodMenu>? = null,
)

@JsonClass(generateAdapter = true)
data class FoodMenu(
    @Json(name = "vMenu") val category: String? = null,
    @Json(name = "menu_items") val items: List<MenuItemData>? = null,
)

data class CompanyInfo(
    val name: String? = null,
    val rating: String? = null,
    val imagePath: String? = null,
    val logoPath: String? = null,
) {
    val imageUrl: String?
        get() = absoluteUrl(imagePath)
    val logoUrl: String?
        get() = absoluteUrl(logoPath) ?: imageUrl
}

@JsonClass(generateAdapter = true)
data class MenuItemData(
    @Json(name = "iMenuItemId") val id: String? = null,
    @Json(name = "vItemType") val name: String? = null,
    @Json(name = "vItemDesc") val description: String? = null,
    @Json(name = "fPrice") val price: String? = null,
    @Json(name = "vImage") val imagePath: String? = null,
    @Json(name = "iMostOrder") val mostOrdered: String? = null,
) {
    val imageUrl: String?
        get() = absoluteUrl(imagePath)
}
