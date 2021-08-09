package com.cryptofuture.londhenet.lib.testing

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cryptofuture.londhenet.lib.testing.rules.MockKRule
import io.mockk.MockK
import io.mockk.MockKDsl
import io.mockk.clearMocks
import org.junit.After
import org.junit.Rule
import org.junit.rules.TestRule
import java.util.*
import kotlin.reflect.KClass

abstract class BaseTest {

    @get:Rule
    val mockkRule = MockKRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    val mockList = ArrayList<Any>()

    @After
    @CallSuper
    fun resetMocks() {
        mockList.forEach {
            clearMocks(it)
        }
    }

    inline fun <reified T : Any> mockk(
        name: String? = null,
        relaxed: Boolean = false,
        vararg moreInterfaces: KClass<*>,
        relaxUnitFun: Boolean = false,
        instance: Boolean = false,
        block: T.() -> Unit = {}
    ): T {
        val mock = MockK.useImpl {
            MockKDsl.internalMockk(
                name,
                relaxed,
                *moreInterfaces,
                relaxUnitFun = relaxUnitFun,
                block = block
            )
        }
        if (!instance) mockList.add(mock)
        return mock
    }
}
