package com.baldeagles.raksha.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.baldeagles.raksha.R
import com.baldeagles.raksha.data.models.SafeHouse
import com.baldeagles.raksha.databinding.FragmentSafeHouseBinding
import com.baldeagles.raksha.ui.viewmodels.MainViewModel

class SafeHouseFragment : Fragment(R.layout.fragment_safe_house) {

    private lateinit var binding: FragmentSafeHouseBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSafeHouseBinding.bind(view)

        binding.txtAddressViaMap.setOnClickListener {
            findNavController().navigate(R.id.action_safeHouseFragment_to_mapFragment)
        }

        sharedViewModel.safeHouseLocation.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.edtSafeHouseAddress.isEnabled = false
                binding.edtSafeHouseAddress.isClickable = false
            } else {
                binding.edtSafeHouseAddress.setText(it.address)
            }
        }

        binding.btnSave.setOnClickListener {
            when {
                binding.edtSafeHouseName.text.isEmpty() -> {
                    binding.edtSafeHouseName.error = "Required"
                }
                binding.edtSafeHouseAddress.text.isEmpty() -> {
                    binding.edtSafeHouseAddress.error = "Required"
                }
                else -> {
                    val locationData = sharedViewModel.safeHouseLocation.value
                    val safeHouse = SafeHouse().apply {
                        locationData?.let {
                            this.address = it.address
                            this.lat = it.lat
                            this.lon = it.long
                        }
                        this.name = binding.edtSafeHouseName.text.toString()
                    }
                    viewModel.insertSafeHouse(safeHouse)
                    findNavController().popBackStack()
                }
            }
        }

    }
}