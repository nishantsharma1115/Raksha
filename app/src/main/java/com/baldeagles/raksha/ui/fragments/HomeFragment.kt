package com.baldeagles.raksha.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baldeagles.raksha.R
import com.baldeagles.raksha.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home){

    private lateinit var binding:FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}