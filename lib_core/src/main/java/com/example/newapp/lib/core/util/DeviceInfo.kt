package com.example.newapp.lib.core.util

import java.util.*
import javax.inject.Inject

class DeviceInfo @Inject constructor() {

    fun locale() = Locale.getDefault().toString()
}
