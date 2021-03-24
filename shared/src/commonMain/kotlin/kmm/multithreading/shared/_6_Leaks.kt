@file:Suppress("ClassName")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback
import kmm.multithreading.shared.util.GC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _6_Leaks : CallWithCallback {

    private val scope = MainScope()

    override fun call(callback: (Any) -> Unit) {
        val job = scope.launch {

            val listOuter: List<Int> = withContext(Dispatchers.Default) {

                val listInner = mutableListOf<Int>()
                repeat(1_000_000) {
                    listInner += it
                }

                listInner
            }

            callback(listOuter.first())
        }

        scope.launch {
            job.join()
            GC.collect()
            withContext(Dispatchers.Default) {
                GC.collect()
            }
        }
    }
}
