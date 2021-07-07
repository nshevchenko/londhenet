package com.example.newapp.lib.core.coroutines

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}
