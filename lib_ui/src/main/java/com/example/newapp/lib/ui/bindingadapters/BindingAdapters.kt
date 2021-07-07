package com.example.newapp.lib.ui.bindingadapters

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

class BindingAdapters {

    @BindingAdapter("isVisible")
    fun isVisible(view: View, value: Boolean) {
        view.visibility = if (value) VISIBLE else GONE
    }
}
