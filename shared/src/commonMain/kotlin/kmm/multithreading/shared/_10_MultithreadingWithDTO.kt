@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.ensureNeverFrozen
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _10_MultithreadingWithDTO : CallWithCallback {

    private val scope = MainScope()

    private val cache = mutableListOf<Any>().apply {
        ensureNeverFrozen()
    }

    override fun call(callback: (Any) -> Unit) {
        scope.launch {

            val dto = DTO(cache.firstOrNull(), cache.lastOrNull())

            val (a2, b2) = withContext(Dispatchers.Default) {
                val (a, b) = dto

                print(a.hashCode() + b.hashCode())

                DTO(null, Any())
            }

            a2?.let(cache::add)
            b2?.let(cache::add)

            callback(cache.size)
        }
    }

    private data class DTO(val a: Any?, val b: Any?)

    // Also, var + copy()

    // Also, stately-isolate + stately-iso-collections
}
