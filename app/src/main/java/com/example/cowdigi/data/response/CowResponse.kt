package com.example.cowdigi.data.response

import com.google.gson.annotations.SerializedName

data class CowResponse(

	@field:SerializedName("input_details")
	val inputDetails: InputDetails,

	@field:SerializedName("predictions")
	val predictions: List<Float>
)

data class InputDetails(

	@field:SerializedName("panjang_badan")
	val panjangBadan: Float,

	@field:SerializedName("bobot_real")
	val bobotReal: Float,

	@field:SerializedName("lebar_dada")
	val lebarDada: Float,

	@field:SerializedName("suhu_badan")
	val suhuBadan: Float
)
