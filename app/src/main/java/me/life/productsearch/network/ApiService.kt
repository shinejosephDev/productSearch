package me.life.productsearch.network

import me.life.productsearch.model.OrderResponse
import me.life.productsearch.model.PrescriptionRequest
import me.life.productsearch.model.SearchResponse
import me.life.productsearch.model.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/api/bot/product/search/")
    suspend fun searchProducts(
        @Query("term") term: String
    ): SearchResponse


    @GET("/api/bot/recent-orders/971526050392")
    suspend fun getOrders(): OrderResponse


    @POST("/api/bot/prescription/create")
    suspend fun createPrescription(
        @Body request: PrescriptionRequest
    ) :Response<Unit>

    @Multipart
    @POST("/api/bot/files/upload")
    suspend fun fileUpload(
        @Part part: MultipartBody.Part
    ) : UploadResponse
}