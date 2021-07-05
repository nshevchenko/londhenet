package com.ford.fcsdriverinterface.lib.core.util

import java.util.*
import javax.inject.Inject

class Language @Inject constructor() {

    fun locale() = Locale.getDefault().toString()
}
