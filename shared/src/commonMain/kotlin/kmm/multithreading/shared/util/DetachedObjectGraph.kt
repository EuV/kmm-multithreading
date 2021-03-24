package kmm.multithreading.shared.util

expect class DetachedObjectGraph<T>(mode: TransferMode, producer: () -> T)

expect inline fun <reified T> DetachedObjectGraph<T>.attach(): T

expect enum class TransferMode {
    SAFE,
    UNSAFE
}
