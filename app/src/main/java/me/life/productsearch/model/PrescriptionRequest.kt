package me.life.productsearch.model

data class PrescriptionRequest(
    val name: String?,
    val mobile_number: String?,
    val flat_number: String?,
    val building: String?,
    val street_address: String?,
    val emirates_id_back: String?,
    val emirates_id_front: String?,
    val insurance_card_back: String?,
    val insurance_card_front: String?,
    val notes: String?,
    val prescription: List<String?>?
)
