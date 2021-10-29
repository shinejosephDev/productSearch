package me.life.productsearch.model

data class OrderResponse(
	val data: List<OrderDataItem?>? = null,
	val success: Boolean? = null,
	val message: String? = null
)

data class OrderDataItem(
	val status_label: String? = null,
	val order_number: String? = null,
	val status: Int? = null
)

