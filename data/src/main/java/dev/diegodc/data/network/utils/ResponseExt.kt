package dev.diegodc.data.network.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> request(block : suspend () -> Response<T>): Response<T> =
    try {
        block.invoke()
    } catch (e: RedirectResponseException) { // 3xx
        Response.Error(e.response.status.description, code= e.response.status.value)
    } catch (e: ClientRequestException) { // 4xx
        Response.Error(e.response.status.description, code= e.response.status.value)
    } catch (e: ServerResponseException) { // 5xx
        Response.Error(e.response.status.description, code= e.response.status.value)
    }
