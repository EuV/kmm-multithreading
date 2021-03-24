package kmm.multithreading.shared.util

actual class DetachedObjectGraph<T> actual constructor(mode: TransferMode, val producer: () -> T)

actual inline fun <reified T> DetachedObjectGraph<T>.attach() = producer()

actual enum class TransferMode {
    SAFE,
    UNSAFE
}
