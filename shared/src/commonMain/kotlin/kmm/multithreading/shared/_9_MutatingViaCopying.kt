@file:Suppress("ClassName", "MemberVisibilityCanBePrivate")

package kmm.multithreading.shared

import co.touchlab.stately.concurrency.AtomicReference
import co.touchlab.stately.concurrency.value
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class _9_MutatingViaCopying : CallWithCallback {

    private val scope = MainScope()
    private val mutex = Mutex()
    private val cache = AtomicReference(listOf<Any>())

    override fun call(callback: (Any) -> Unit) {

        scope.launch {
            get(0)
            add(Any())

            withContext(Dispatchers.Default) {
                get(0)
                add(Any())
            }

            callback(size())
        }
    }

    fun size(): Int {
        return cache.value.size
    }

    fun get(index: Int): Any? {
        return cache.value.getOrNull(index)
    }

    suspend fun add(any: Any) {
        mutex.withLock {
            cache.value += any
        }
    }
}
