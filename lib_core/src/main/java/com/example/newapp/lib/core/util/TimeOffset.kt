package com.ford.fcsdriverinterface.lib.core.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TimeOffset @Inject constructor() {

    fun offset(dateTime: Date = Date()): String {
        return SimpleDateFormat("ZZZZZ", Locale.getDefault()).format(dateTime)
    }
}
