package me.life.productsearch.usecase

import me.life.productsearch.data.SearchRepository
import me.life.productsearch.model.ResultData
import me.life.productsearch.model.SearchResponse
import javax.inject.Inject

class SearchUsecase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend fun getProducts(query : String): ResultData<SearchResponse> {
        val data = searchRepository.searchProducts(query)
        return ResultData.Success(data)
    }
}