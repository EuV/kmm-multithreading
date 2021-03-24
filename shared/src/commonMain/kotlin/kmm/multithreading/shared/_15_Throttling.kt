@file:Suppress("ClassName")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class _15_Throttling : CallWithCallback {

    private val scope = MainScope()

    private var counter = 0L

    override fun call(callback: (Any) -> Unit) {
        scope.launch {
            repeat(10_000) {
                repeat(10_000) {
                    counter++
                }
                yield()
            }
            counter %= 7
            callback(counter)
        }
    }
}
