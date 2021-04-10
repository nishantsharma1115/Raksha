package com.baldeagles.raksha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baldeagles.raksha.R
import com.baldeagles.raksha.util.ResponseStatus
import com.baldeagles.raksha.viewmodels.AddressViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private var latLng = LatLng(25.1933895, 66.5949836)
    private var mGoogleMap: GoogleMap? = null
    private lateinit var viewModel: AddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapsInitializer.initialize(context)
        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        viewModel.getLocationInformation.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    ResponseStatus.ERROR -> {
                        //binding.addressEditText.setText("Location not found!")
                    }
                    ResponseStatus.LOADING -> {
                        //binding.addressEditText.setText("Searching...")
                    }
                    ResponseStatus.SUCCESS -> {
                        it.data?.let {
                            //binding.addressEditText.setText(model.locationAddress)
                        }
                    }
                }
            }
        })
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