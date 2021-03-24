package kmm.multithreading.shared.util

import kotlin.native.concurrent.DetachedObjectGraph as DetachedObjectGraphNative
import kotlin.native.concurrent.TransferMode as TransferModeNative
import kotlin.native.concurrent.attach as attachNative

actual typealias DetachedObjectGraph<T> = DetachedObjectGraphNative<T>

actual inline fun <reified T> DetachedObjectGraph<T>.attach() = attachNative()

actual typealias TransferMode = TransferModeNative
