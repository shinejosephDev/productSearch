package me.life.productsearch.model

data class SearchResponse(
	val data: List<DataItem?>? = null,
	val success: Boolean? = null,
	val message: String? = null
)

data class DataItem(
	val image: String? = null,
	val price: String? = null,
	val link: String? = null,
	val title: String? = null
)

