@file:Suppress("ClassName")

package kmm.multithreading.shared

import kmm.multithreading.shared.util.CallWithCallback

class _0_MicCheck : CallWithCallback {

    override fun call(callback: (Any) -> Unit) {
        callback(0)
    }
}
