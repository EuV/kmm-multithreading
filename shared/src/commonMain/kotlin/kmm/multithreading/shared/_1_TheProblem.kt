@file:Suppress("ClassName")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class _1_TheProblem : CallWithCallback {

    private val scope = MainScope()

    private var counter = 0

    override fun call(callback: (Any) -> Unit) {
        scope.launch {
            counter++

            withContext(Dispatchers.Default) { // native-mt
                counter % 2
            }

            callback(counter)
        }
    }
}

