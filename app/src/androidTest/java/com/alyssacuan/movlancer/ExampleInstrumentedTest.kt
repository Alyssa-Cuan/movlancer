package com.alyssacuan.movlancer

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented movie_item_layout, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under movie_item_layout.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.alyssacuan.movlancer", appContext.packageName)
    }
}
