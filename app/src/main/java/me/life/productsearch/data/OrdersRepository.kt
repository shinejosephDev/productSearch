package me.life.productsearch.data

import me.life.productsearch.model.OrderResponse
import me.life.productsearch.model.SearchResponse
import me.life.productsearch.network.ApiService
import javax.inject.Inject

class OrdersRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMyOrders(): OrderResponse {
        return apiService.getOrders()
    }
}