@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.ensureNeverFrozen
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class _13_ImmutableIsNotFrozen : CallWithCallback {

    private val list = mutableListOf(Any()).apply {
        ensureNeverFrozen()
    }

    override fun call(callback: (Any) -> Unit) {
        list += Any()
        log(list)
    }
}

private fun log(list: List<Any>) {
    GlobalScope.launch {
        list.size
    }
}
