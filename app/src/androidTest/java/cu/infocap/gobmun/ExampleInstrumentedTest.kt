package cu.infocap.gobmun

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented contact_layout, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under contact_layout.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("cu.infocap.gobmun", appContext.packageName)
    }
}
