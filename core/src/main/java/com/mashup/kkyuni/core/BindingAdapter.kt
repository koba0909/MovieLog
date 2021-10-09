package com.mashup.kkyuni.core

import android.view.View
import androidx.databinding.BindingAdapter
import com.mashup.kkyuni.core.custom.ThrottleClickListener

@BindingAdapter(requireAll = false, value =["onThrottleClick", "throttleInterval"])
fun onThrottleClick(
    view: View,
    listener: View.OnClickListener,
    interval: Long?
){
    view.setOnClickListener(
        ThrottleClickListener(
            listener,
            interval ?: 1000L
        )
    )
}