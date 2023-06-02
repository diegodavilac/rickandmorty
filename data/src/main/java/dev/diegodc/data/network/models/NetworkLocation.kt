package dev.diegodc.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLocation(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)