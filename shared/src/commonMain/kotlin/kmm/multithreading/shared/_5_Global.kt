@file:Suppress("ClassName", "VARIABLE_IN_SINGLETON_WITHOUT_THREAD_LOCAL")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.native.concurrent.SharedImmutable
import kotlin.native.concurrent.ThreadLocal

@SharedImmutable // or @ThreadLocal
private val staticAny = Any()

class _5_Global : CallWithCallback {

    private val scope = MainScope()

    override fun call(callback: (Any) -> Unit) {
        scope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Default) {
                staticAny
            }
        }
        Obj.inc()
    }
}

@ThreadLocal
private object Obj {
    private var counter = 0

    fun inc() {
        counter++
    }
}
