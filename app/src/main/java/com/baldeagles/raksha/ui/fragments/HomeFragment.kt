package com.baldeagles.raksha.ui.fragments

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.baldeagles.raksha.R
import com.baldeagles.raksha.databinding.FragmentHomeBinding
import com.baldeagles.raksha.ui.adapters.SafeHouseAdapter
import com.baldeagles.raksha.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var safeHouseAdapter: SafeHouseAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        safeHouseAdapter = SafeHouseAdapter()

        binding.safeHouseRecycle.apply {
            adapter = safeHouseAdapter
            setHasFixedSize(false)
        }

        binding.callPoliceBtn.setOnClickListener {
            makePhoneCall()
        }

        binding.whatsappBtn.setOnClickListener {
            sendWhatsAppMessage()
        }

        val simpleItemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Timber.d("dragged")
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteSafeHouse(safeHouseAdapter.currentList[position])
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.safeHouseRecycle)

        binding.viewInMapButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_viewSafeHousesInMapFragment)
        }

        binding.addSafeHouseFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_safeHouseFragment)
        }

        viewModel.safeHouses.observe(viewLifecycleOwner) {
            safeHouseAdapter.submitList(it)
        }

    }

    private fun sendWhatsAppMessage() {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Help me... I am in Danger")
        try {
            requireActivity().startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Whatsapp is not installed :(", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun makePhoneCall() {
        val phoneNumber = "1091"
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
        } else {
            val dial = "tel:$phoneNumber"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            }
        }
    }
}