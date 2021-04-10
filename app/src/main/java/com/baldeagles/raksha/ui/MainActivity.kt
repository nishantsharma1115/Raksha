package com.baldeagles.raksha.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.baldeagles.raksha.R
import com.baldeagles.raksha.databinding.ActivityMainBinding
import com.baldeagles.raksha.ui.viewmodels.MainViewModel
import com.baldeagles.raksha.utils.Resource
import com.baldeagles.raksha.utils.showToast
import com.vaibhav.nextlife.utils.location.GpsUtils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()
    var isGPSEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragment)
        GpsUtils(this).turnGPSOn(object : GpsUtils.OnGpsListener {

            override fun gpsStatus(isGPSEnable: Boolean) {
                this@MainActivity.isGPSEnabled = isGPSEnable
            }
        })
        invokeLocationAction()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPSEnabled = true
            }
        }
    }

    private fun invokeLocationAction() {
        if (isPermissionsGranted()) {
            when {
                !isGPSEnabled -> this.showToast("Enable GPS and restart app")

                isPermissionsGranted() -> startLocationUpdate()

                shouldShowRequestPermissionRationale() -> this.showToast(
                    "Give permissions for app to continue"
                )
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_REQUEST
            )
        }

    }

    private fun startLocationUpdate() {
        viewModel.startListeningToLocation()
        viewModel.location.observe(this, {
            when (it) {
                is Resource.Error -> {
                    Timber.d("Error")
                }
                is Resource.Loading -> Timber.d("Loading")
                is Resource.Success -> {
                    it.data?.let { location ->
                        Timber.d("${location.lat} ${location.long} ${location.address} ${location.city}")
                        viewModel.setLocation(location)
                    }

                }
            }

        })
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRequestPermissionRationale() =
        ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) && ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }
}

const val LOCATION_REQUEST = 100
const val GPS_REQUEST = 10
