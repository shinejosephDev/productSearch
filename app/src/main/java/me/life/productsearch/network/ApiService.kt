package me.life.productsearch.network

import me.life.productsearch.model.OrderResponse
import me.life.productsearch.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/bot/product/search/")
    suspend fun searchProducts (
        @Query("term") term: String
    ) : SearchResponse


    @GET("/api/bot/recent-orders/971526050392")
    suspend fun getOrders () : OrderResponse
}