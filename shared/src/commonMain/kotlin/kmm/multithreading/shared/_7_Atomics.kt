@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.concurrency.AtomicBoolean
import co.touchlab.stately.concurrency.AtomicReference
import co.touchlab.stately.concurrency.value
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class _7_Atomics : CallWithCallback {

    private val scope = MainScope()

    private val initial = Any()

    private val aBoolean = AtomicBoolean(false)
    private val aReference = AtomicReference(initial)

    override fun call(callback: (Any) -> Unit) {
        scope.launch {
            if (aBoolean.value) {
                aReference.value = Any()
                aBoolean.value = false
            }
        }

        scope.launch(Dispatchers.Default) {
            aReference.compareAndSet(initial, Any())
        }
    }
}
