package com.example.cowdigi.data.response

data class CowResponse(
	val inputDetails: InputDetails,
	val predictions: List<Any>
)

data class InputDetails(
	val panjangBadan: Int,
	val bobotReal: Int,
	val lebarDada: Int,
	val suhuBadan: Int
)

