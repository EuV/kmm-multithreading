@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.ensureNeverFrozen
import kmm.multithreading.shared.util.CallWithCallback
import kmm.multithreading.shared.util.DetachedObjectGraph
import kmm.multithreading.shared.util.TransferMode
import kmm.multithreading.shared.util.attach
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _11_PureMutable : CallWithCallback {

    private val scope = MainScope()

    private var cache: MutableList<Any>? = mutableListOf<Any>().apply {
        ensureNeverFrozen()
    }

    override fun call(callback: (Any) -> Unit) {
        scope.launch {

            repeat(1000) {

                cache?.add(Any())

                val fromUiToBg = DetachedObjectGraph(TransferMode.SAFE) {
                    cache.also { cache = null }
                }

                val fromBgToUi = withContext(Dispatchers.Default) {
                    var cacheOnBg = fromUiToBg.attach()

                    cacheOnBg?.add(Any())

                    DetachedObjectGraph(TransferMode.SAFE) {
                        cacheOnBg.also { cacheOnBg = null }
                    }
                }

                cache = fromBgToUi.attach()
            }

            callback(cache?.size ?: "null")
        }
    }
}
