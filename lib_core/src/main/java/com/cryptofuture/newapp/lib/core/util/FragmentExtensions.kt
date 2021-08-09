package com.cryptofuture.londhenet.lib.core.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observe(liveData: LiveData<T>, body: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { it?.let { body(it) } })
}