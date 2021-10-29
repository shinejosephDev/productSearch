package me.life.productsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.life.productsearch.model.ResultData
import me.life.productsearch.model.SearchResponse
import me.life.productsearch.usecase.SearchUsecase
import javax.inject.Inject


@HiltViewModel
class ProductSearchViewModel @Inject constructor(private var usecase: SearchUsecase) : ViewModel() {
    private val _response: MutableLiveData<ResultData<SearchResponse>> = MutableLiveData()
    val response: LiveData<ResultData<SearchResponse>> = _response

    fun searchProducts(query: String) = viewModelScope.launch {
        _response.value = usecase.getProducts(query)
    }
}