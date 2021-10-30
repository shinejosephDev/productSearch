package me.life.productsearch.data

import me.life.productsearch.model.PrescriptionRequest
import me.life.productsearch.model.UploadResponse
import me.life.productsearch.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject


class PrescriptionRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun createPrescription(request: PrescriptionRequest): Response<Unit> {
        return apiService.createPrescription(request)
    }

    suspend fun uploadImage(file:File): UploadResponse {
//        val file = File("/storage/emulated/0/Download/Corrections 6.jpg")
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("file", file.getName(), requestFile)
        return apiService.fileUpload(body)
    }

}