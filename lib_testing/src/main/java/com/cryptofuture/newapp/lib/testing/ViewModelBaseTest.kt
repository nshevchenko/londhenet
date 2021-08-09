package com.cryptofuture.londhenet.lib.testing

import com.cryptofuture.londhenet.lib.core.coroutines.CoroutineDispatchers
import com.cryptofuture.londhenet.lib.testing.BaseTest
import io.mockk.every
import kotlinx.coroutines.Dispatchers

abstract class ViewModelBaseTest : BaseTest() {

    val mockCoroutineDispatchers: CoroutineDispatchers = mockk {
        every { main } returns Dispatchers.Unconfined
        every { io } returns Dispatchers.Unconfined
        every { default } returns Dispatchers.Unconfined
    }
}
