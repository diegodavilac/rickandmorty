package dev.diegodc.core.common.exceptions

open class CustomException(
    override val message: String?
) : Exception(message)

class NetworkException(message: String?, val code: Int) : CustomException(message)