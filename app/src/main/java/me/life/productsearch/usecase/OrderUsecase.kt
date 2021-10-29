package me.life.productsearch.usecase

import me.life.productsearch.data.OrdersRepository
import me.life.productsearch.model.OrderResponse
import me.life.productsearch.model.ResultData
import javax.inject.Inject

class OrderUsecase @Inject constructor(private val repository: OrdersRepository) {
    suspend fun getMyOrders(): ResultData<OrderResponse> {
        val data = repository.getMyOrders()
        return ResultData.Success(data)
    }
}