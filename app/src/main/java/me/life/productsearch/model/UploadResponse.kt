package me.life.productsearch.model

data class UploadResponse(
    val success: Boolean?,
    val message: String?,
    val data: Data?
)

data class Data(
    val file: String?
)
