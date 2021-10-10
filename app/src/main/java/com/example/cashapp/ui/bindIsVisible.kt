package com.example.cashapp.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun bindIsVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
