package me.life.productsearch.data

import me.life.productsearch.model.SearchResponse
import me.life.productsearch.network.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun searchProducts(query: String): SearchResponse {
        return apiService.searchProducts(query)
    }
}