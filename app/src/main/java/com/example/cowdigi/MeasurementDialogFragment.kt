package com.example.cowdigi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.fragment.app.DialogFragment
import com.example.cowdigi.databinding.DialogMeasurementBinding

class MeasurementDialogFragment : DialogFragment() {
    private lateinit var binding: DialogMeasurementBinding
    private val viewModel: MainViewModel by viewModels()

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialog?.setCancelable(false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_background)
        binding = DialogMeasurementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hasil.visibility = View.INVISIBLE

        viewModel.hasil.observe(viewLifecycleOwner) {
            val formattedPredictions = it?.predictions?.joinToString(separator = ", ") {
                String.format("%.2f", it) // Format each prediction to 2 decimal places
            }
            binding.hasil.text = "Hasil Prediksi: $formattedPredictions"
            binding.hasil.visibility = View.VISIBLE
        }

        // Tombol hitung di dialog fragment
        binding.buttonUkur.setOnClickListener {
            val lingkarText = binding.edLingkartubuh.text.toString()
            val panjangText = binding.edPanjangtubuh.text.toString()
            val bobotText = binding.edBobotreal.text.toString()
            val suhuText = binding.edSuhubadan.text.toString()

            try {
                val lingkarTubuh = lingkarText.toDouble()
                val panjangTubuh = panjangText.toDouble()
                val bobotTubuh = bobotText.toInt()
                val suhuTubuh = suhuText.toInt()

                binding.edLingkartubuh.clearFocus()
                binding.edPanjangtubuh.clearFocus()
                binding.edBobotreal.clearFocus()
                binding.edSuhubadan.clearFocus()

                viewModel.hitungPenjumlahan(lingkarTubuh, panjangTubuh,bobotTubuh, suhuTubuh)
            } catch (e: NumberFormatException) {
                Toast.makeText(requireContext(), "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }
}
