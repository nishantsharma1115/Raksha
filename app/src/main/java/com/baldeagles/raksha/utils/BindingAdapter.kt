package com.baldeagles.raksha.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setOnBoardingImage")
fun ImageView.setOnBoardingImage(resource: Int) {
    Glide.with(this).load(resource).into(this)
}