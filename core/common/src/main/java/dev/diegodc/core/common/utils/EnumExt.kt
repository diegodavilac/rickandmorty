package dev.diegodc.core.common.utils

inline fun <reified T : Enum<T>> String.asEnumOrNull(defaultValue: T?): T? =
    enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) }