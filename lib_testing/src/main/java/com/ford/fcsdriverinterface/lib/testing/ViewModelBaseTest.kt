package com.ford.fcsdriverinterface.lib.testing

import com.ford.fcsdriverinterface.lib.core.coroutines.CoroutineDispatchers
import io.mockk.every
import kotlinx.coroutines.Dispatchers

abstract class ViewModelBaseTest : BaseTest() {

    val mockCoroutineDispatchers: CoroutineDispatchers = mockk {
        every { main } returns Dispatchers.Unconfined
        every { io } returns Dispatchers.Unconfined
        every { default } returns Dispatchers.Unconfined
    }
}
