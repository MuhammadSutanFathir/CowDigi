package com.example.cowdigi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cowdigi.data.response.CowResponse
import com.example.cowdigi.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _hasil = MutableLiveData<CowResponse>()
    val hasil: LiveData<CowResponse> get() = _hasil

    fun hitungPenjumlahan(lingkarTubuh: Double, panjangTubuh: Double) {
        // Format the input array to match the expected structure
        val input = mapOf("input" to arrayOf(arrayOf(lingkarTubuh, panjangTubuh)))

        viewModelScope.launch {
            try {
                val response: CowResponse = ApiConfig.getApiService().predictWeight(input)
                _hasil.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
