@file:Suppress("ClassName")

package kmm.multithreading.shared

import co.touchlab.stately.freeze
import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class _4_Freeze : CallWithCallback {

    private val scope = MainScope()

    private val immutable = 42

    init {
        freeze()
    }

    override fun call(callback: (Any) -> Unit) {
        scope.launch(Dispatchers.Default) {
            print(immutable % 2)
        }
    }
}
