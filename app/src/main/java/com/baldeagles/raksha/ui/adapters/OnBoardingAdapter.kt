package com.baldeagles.raksha.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.baldeagles.raksha.data.models.OnBoarding
import com.baldeagles.raksha.databinding.OnboardingLayoutBinding


class OnBoardingAdapter(
    private val list: List<OnBoarding>,
    private val onContinueClicked: () -> Unit
) :
    RecyclerView.Adapter<OnBoardingAdapter.onBoardingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onBoardingViewHolder {
        val binding = LayoutInflater.from(parent.context)
        return onBoardingViewHolder(OnboardingLayoutBinding.inflate(binding, parent, false))
    }

    override fun onBindViewHolder(holder: onBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class onBoardingViewHolder(private val binding: OnboardingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.continueButton.setOnClickListener {
                onContinueClicked()
            }
        }

        fun bind(onboarding: OnBoarding) {
            binding.continueButton.isVisible = adapterPosition == list.size - 1
            binding.onboarding = onboarding
        }


    }

}
