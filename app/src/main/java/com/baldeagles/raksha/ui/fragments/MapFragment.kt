package com.baldeagles.raksha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.baldeagles.raksha.R
import com.baldeagles.raksha.databinding.FragmentMapBinding
import com.baldeagles.raksha.ui.viewmodels.AddressViewModel
import com.baldeagles.raksha.ui.viewmodels.MainViewModel
import com.baldeagles.raksha.util.ResponseStatus
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    //get this default lat lng value from mainViewModel
    private var latLng = LatLng(25.1933895, 66.5949836)
    private var mGoogleMap: GoogleMap? = null
    private lateinit var binding: FragmentMapBinding
    private val viewModel: AddressViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var isAddressFetched: Boolean = false
    //when user confirms location then save in the safeHouseLocation LiveData and navigate back

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        MapsInitializer.initialize(context)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        viewModel.getLocationInformationForMap.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.ERROR -> {
                        binding.edtLocation.setText("Location not found!")
                        isAddressFetched = false
                    }
                    ResponseStatus.LOADING -> {
                        binding.edtLocation.setText("Searching..")
                        isAddressFetched = false
                    }
                    ResponseStatus.SUCCESS -> {
                        binding.edtLocation.setText(it.data?.address)
                        it.data?.let { data ->
                            sharedViewModel.setLocationForSafeHouse(data)
                        }
                        isAddressFetched = true
                    }
                }
            }
        })

        binding.btnDone.setOnClickListener {
            if (isAddressFetched) {
                findNavController().popBackStack()
            } else {
                binding.edtLocation.error = "Invalid Address"
            }
        }
    }

    override fun onCameraIdle() {
        mGoogleMap?.let {
            it.cameraPosition?.let {
                latLng = mGoogleMap?.cameraPosition!!.target

                viewModel.getLocationInfo(
                    requireContext(),
                    latLng.latitude.toString(),
                    latLng.longitude.toString()
                )
            }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            mGoogleMap = it
            mGoogleMap?.clear()
            mGoogleMap?.setOnCameraIdleListener(this)
            animateCamera()
        }
    }

    private fun animateCamera() {
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
    }
}