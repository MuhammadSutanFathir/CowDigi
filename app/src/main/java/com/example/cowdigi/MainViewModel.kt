package com.example.cowdigi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cowdigi.data.response.CowResponse
import com.example.cowdigi.data.retrofit.ApiConfig
import com.example.cowdigi.data.retrofit.PredictRequest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _hasil = MutableLiveData<CowResponse?>()
    val hasil: LiveData<CowResponse?> get() = _hasil

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun hitungPenjumlahan(lebarDada: Float, panjangBadan: Float, bobotReal: Float, suhuBadan: Float) {
        _isLoading.value = true
        _errorMessage.value = null

        // Format the request object
        val request = PredictRequest(
            input = arrayOf(arrayOf(lebarDada, panjangBadan)),
            bobot_real = bobotReal,
            suhu_badan = suhuBadan
        )

        viewModelScope.launch {
            try {
                val response: CowResponse = ApiConfig.getApiService().predictWeight(request)
                Log.d("MainViewModel", "Response: $response")
                _hasil.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.localizedMessage ?: "Terjadi kesalahan."
                _hasil.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

