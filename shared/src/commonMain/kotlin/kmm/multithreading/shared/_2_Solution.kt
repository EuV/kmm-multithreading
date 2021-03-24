@file:Suppress("ClassName")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _2_Solution : CallWithCallback {

    private val scope = MainScope()

    private var counter = 0

    override fun call(callback: (Any) -> Unit) {
        scope.launch {
            counter++

            val frozenCounter = counter
            withContext(Dispatchers.Default) {
                // this@_2_Solution.counter % 2
                frozenCounter % 2
                // this@_2_Solution.doSomeMath()
                doSomeStaticMath()
            }

            doSomeMathWithArg(counter)

            callback(counter)
        }
    }

    private fun doSomeMath() = 42

    private suspend fun doSomeMathWithArg(arg: Int) {
        withContext(Dispatchers.Default) {
            arg % 2
        }
    }

    private suspend fun trampoline() {
        withContext(Dispatchers.Default) {
            val a = 42

            val b = withContext(Dispatchers.Main) {
                a + counter // Frozen
            }

            b % 2
        }
    }

    private suspend fun trampolineFixed() {
        val a = withContext(Dispatchers.Default) {
            42
        }

        val b = a + counter

        withContext(Dispatchers.Default) {
            b % 2
        }
    }
}

private fun doSomeStaticMath() = 42
