package com.kool.customer.data.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.kool.customer.data.model.RestaurantsResponse
import com.kool.customer.data.model.StoreDetailsResponse

interface KoolApi {
    @FormUrlEncoded
    @POST("webservice_shark.php")
    suspend fun loadAvailableRestaurants(
        @Field("type") type: String = "loadAvailableRestaurants",
        @Field("PassengerLat") lat: Double = 32.9,
        @Field("PassengerLon") lon: Double = 13.18,
        @Field("iUserId") userId: Int = 0,
        @Field("cuisineId") cuisineId: Int = 0,
        @Field("iCategoryId") categoryId: Int = 0,
        @Field("vAddress") address: String = "طرابلس",
        @Field("vLang") lang: String = "ar",
        @Field("UserType") userType: String = "Customer",
        @Field("page") page: Int = 1,
    ): RestaurantsResponse

    @FormUrlEncoded
    @POST("webservice_shark.php")
    suspend fun getStoreDetails(
        @Field("type") type: String = "GetRestaurantDetails",
        @Field("iCompanyId") companyId: Int,
        @Field("iUserId") userId: Int = 0,
        @Field("vLang") lang: String = "ar",
        @Field("eSystem") system: String = "DeliverAll",
    ): StoreDetailsResponse
}
