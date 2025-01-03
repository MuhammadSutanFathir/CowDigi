package com.example.cowdigi.data.retrofit

import com.example.cowdigi.data.response.CowResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

data class PredictRequest(
    val input: Array<Array<Float>>,
    val bobot_real: Float,
    val suhu_badan: Float
)

interface ApiService {
    @POST("/predict")
    suspend fun predictWeight(
        @Body input: PredictRequest
    ): CowResponse
}


