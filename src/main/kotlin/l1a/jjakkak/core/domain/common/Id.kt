package l1a.jjakkak.core.domain.common

import java.util.*
import kotlin.reflect.full.primaryConstructor

interface IdType<T> {
    val value: T
}

interface IdTypeUUID: IdType<UUID> {
    companion object {
        inline fun <reified T: IdTypeUUID> random(): T =
            T::class.primaryConstructor
                ?.call(UUID.randomUUID())
                ?: throw IllegalArgumentException("No primary constructor found for class ${T::class.simpleName}")
    }
}

interface IdTypeString: IdType<String>