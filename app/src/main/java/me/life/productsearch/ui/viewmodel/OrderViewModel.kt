package me.life.productsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.life.productsearch.model.OrderResponse
import me.life.productsearch.model.ResultData
import me.life.productsearch.usecase.OrderUsecase
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private var usecase: OrderUsecase) : ViewModel() {
    private val _response: MutableLiveData<ResultData<OrderResponse>> = MutableLiveData()
    val response: LiveData<ResultData<OrderResponse>> = _response

    fun searchProducts() = viewModelScope.launch {
        _response.value = usecase.getMyOrders()
    }
}