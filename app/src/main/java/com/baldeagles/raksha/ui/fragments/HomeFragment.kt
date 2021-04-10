package com.baldeagles.raksha.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.baldeagles.raksha.R
import com.baldeagles.raksha.data.models.SafeHouse
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
}