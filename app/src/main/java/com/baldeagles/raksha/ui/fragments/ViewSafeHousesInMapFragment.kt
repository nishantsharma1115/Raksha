package com.baldeagles.raksha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.baldeagles.raksha.R
import com.baldeagles.raksha.ui.viewmodels.MainViewModel
import com.baldeagles.raksha.utils.showToast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewSafeHousesInMapFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        viewModel.userLocation.value?.let {
            val myLoc = LatLng(it.lat, it.long)
            val update = CameraUpdateFactory.newLatLngZoom(myLoc, 15f)
            googleMap.animateCamera(update)
        } ?: requireContext().showToast("Cannot fetch location please try again")

        viewModel.safeHouses.value?.forEach {
            val safeHouseLoc = LatLng(it.lat, it.lon)
            googleMap.addMarker(MarkerOptions().position(safeHouseLoc).title(it.name))
        } ?: requireContext().showToast("No Safe houses saved")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_safehouses_in_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}