@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.concurrency.AtomicInt
import co.touchlab.stately.concurrency.value
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class _8_Mutex : CallWithCallback {

    private val scope = MainScope()
    private val mutex = Mutex()
    private val aInt = AtomicInt(0)

    override fun call(callback: (Any) -> Unit) {

        val job = scope.launch {
            repeat(1_000) {

                launch {
                    mutex.withLock {
                        aInt.value++
                    }
                }

                launch(Dispatchers.Default) {
                    mutex.withLock {
                        aInt.value++
                    }
                }
            }
        }

        scope.launch {
            job.join()
            callback(aInt.value)
        }
    }
}
