package kmm.multithreading.shared.util

interface CallWithCallback {
    fun call(callback: (Any) -> Unit)
}
