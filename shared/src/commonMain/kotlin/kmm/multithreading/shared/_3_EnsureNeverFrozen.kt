@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.ensureNeverFrozen
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _3_EnsureNeverFrozen : CallWithCallback {

    private val scope = MainScope()

    private var counter = 0

    init {
        ensureNeverFrozen() // co.touchlab.stately
    }

    override fun call(callback: (Any) -> Unit) {
        scope.launch {
            counter++

            val frozenCounter = counter
            withContext(Dispatchers.Default) {
                print(frozenCounter % 2)
            }

            callback(counter)
        }
    }
}
