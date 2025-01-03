package com.example.cowdigi.data.retrofit

import com.example.cowdigi.data.response.CowResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/predict")
    suspend fun predictWeight(
        @Body input:Map<String,Array<Array<Double>>>
    ): CowResponse
}

