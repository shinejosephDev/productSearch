package me.life.productsearch.usecase

import me.life.productsearch.data.PrescriptionRepository
import me.life.productsearch.model.PrescriptionRequest
import me.life.productsearch.model.ResultData
import me.life.productsearch.model.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class PrescriptionUsecase @Inject constructor(private val prescriptionRepository: PrescriptionRepository) {

    suspend fun submitPrescription(prescriptionRequest: PrescriptionRequest): Response<Unit> {
        val data = prescriptionRepository.createPrescription(prescriptionRequest)
        return data
    }

    suspend fun fileUpload(file: File): ResultData<UploadResponse> {
        val data = prescriptionRepository.uploadImage(file)
        return ResultData.Success(data)
    }

}