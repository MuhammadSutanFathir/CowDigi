package com.example.cowdigi.data.response

import com.google.gson.annotations.SerializedName

data class CowResponse(

	@field:SerializedName("predictions")
	val predictions: List<Any>
)
