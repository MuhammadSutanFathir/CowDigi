package com.example.cowdigi

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.fragment.app.DialogFragment
import com.example.cowdigi.databinding.DialogMeasurementBinding
import java.util.Locale

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

        binding.linear2.visibility = View.GONE
        binding.linear1.visibility = View.VISIBLE

        viewModel.hasil.observe(viewLifecycleOwner) {
            val formattedPredictions = it?.predictions?.joinToString(separator = ", ") {
                String.format(Locale.US,"%.2f", it) // Format each prediction to 2 decimal places
            }
            binding.hasil.text = "Hasil Prediksi: $formattedPredictions"
            binding.linear1.visibility = View.GONE
            binding.linear2.visibility = View.VISIBLE

            it?.inputDetails.let {
                binding.tvLingkarTubuh.text = "Lingkar Tubuh: ${it?.lebarDada}"
                binding.tvPanjangTubuh.text = "Panjang Tubuh: ${it?.panjangBadan}"
                binding.tvBobotReal.text = "Bobot Real: ${it?.bobotReal}"
                binding.tvSuhuBadan.text = "Suhu Badan: ${it?.suhuBadan}"
            }

        }


        // Tombol hitung di dialog fragment
        binding.buttonUkur.setOnClickListener {
            val lingkarText = binding.edLingkartubuh.text.toString()
            val panjangText = binding.edPanjangtubuh.text.toString()
            val bobotText = binding.edBobotreal.text.toString()
            val suhuText = binding.edSuhubadan.text.toString()

            try {
                val lingkarTubuh = lingkarText.toFloat()
                val panjangTubuh = panjangText.toFloat()
                val bobotTubuh = bobotText.toFloat()
                val suhuTubuh = suhuText.toFloat()

                binding.edLingkartubuh.clearFocus()
                binding.edPanjangtubuh.clearFocus()
                binding.edBobotreal.clearFocus()
                binding.edSuhubadan.clearFocus()

                val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

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
