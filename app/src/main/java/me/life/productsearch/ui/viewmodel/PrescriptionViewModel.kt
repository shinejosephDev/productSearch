package me.life.productsearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.life.productsearch.model.PrescriptionRequest
import me.life.productsearch.model.ResultData
import me.life.productsearch.usecase.PrescriptionUsecase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PrescriptionViewModel @Inject constructor(private var usecase: PrescriptionUsecase) :
    ViewModel() {

    companion object {
        const val TYPE_EID = "type_eid"
        const val TYPE_INSURANCE = "type_insurance"
        const val TYPE_PRESCRIPTION = "type_prescription"
    }

    private val _response: MutableLiveData<ResultData<Unit>> = MutableLiveData()
    val response: LiveData<ResultData<Unit>> = _response

    private val _fileresponse: MutableLiveData<ResultData<Unit>> = MutableLiveData()
    val fileResponse: LiveData<ResultData<Unit>> = _fileresponse

    var selectedType: String = ""

    var eidList: ArrayList<String> = ArrayList()
    var insuranceList: ArrayList<String> = ArrayList()
    var prescriptionList: ArrayList<String> = ArrayList()

    fun submit(prescriptionRequest: PrescriptionRequest) {
        viewModelScope.launch {
            val response = usecase.submitPrescription(prescriptionRequest)
            if (response.isSuccessful) {
                _response.postValue(ResultData.Success())
            } else {
                _response.postValue(ResultData.Error())
            }
        }
    }

    fun fileUpload(file: File) {
        viewModelScope.launch {
            val response = usecase.fileUpload(file)
            if (response is ResultData.Success) {
                _fileresponse.postValue(ResultData.Success())
                when (selectedType) {
                    TYPE_EID -> {
                        response.data?.data?.file?.let { eidList.add(it) }
                    }
                    TYPE_INSURANCE -> {
                        response.data?.data?.file?.let { insuranceList.add(it) }
                    }
                    TYPE_PRESCRIPTION -> {
                        response.data?.data?.file?.let { prescriptionList.add(it) }
                    }
                }
            } else {
                _fileresponse.postValue(ResultData.Error())
            }
        }
    }

}