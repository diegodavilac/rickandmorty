package dev.diegodc.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val info: NetworkInfo,
    val results: T
)
