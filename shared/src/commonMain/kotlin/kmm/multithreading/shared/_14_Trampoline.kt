@file:Suppress("ClassName")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _14_Trampoline : CallWithCallback {

    private val scope = MainScope()

    private var counter = 0

    override fun call(callback: (Any) -> Unit) {
        scope.launch {
            trampolineFixed()
            callback(counter)
        }
    }

    private suspend fun trampoline() {
        counter = withContext(Dispatchers.Default) {
            val a = 42

            val b = withContext(Dispatchers.Main) {
                a + counter // Frozen
            }

            b % 5
        }
    }

    private suspend fun trampolineFixed() {
        val a = withContext(Dispatchers.Default) {
            42
        }

        val b = a + counter

        counter = withContext(Dispatchers.Default) {
            b % 5
        }
    }
}
