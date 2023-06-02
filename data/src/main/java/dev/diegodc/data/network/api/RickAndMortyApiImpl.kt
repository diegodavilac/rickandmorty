package dev.diegodc.data.network.api

import dev.diegodc.data.network.RickAndMortyApi
import dev.diegodc.data.network.models.NetworkCharacter
import dev.diegodc.data.network.models.NetworkResponse
import dev.diegodc.data.network.utils.Response
import dev.diegodc.data.network.utils.request
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject

class RickAndMortyApiImpl @Inject constructor(
    private val client: HttpClient
) : RickAndMortyApi {

    override suspend fun getCharacters(
        page: Int,
        params: Map<String, String?>
    ): Response<List<NetworkCharacter>> = request {
        val result: NetworkResponse<List<NetworkCharacter>> = client.get {
            url {
                path("/api/character")
                parameters.append("page", page.toString())
                params.forEach {
                    if (it.value.isNullOrEmpty().not()) {
                        parameters.append(it.key, it.value!!)
                    }
                }
            }

        }.body()

        return@request Response.Success(result.results)
    }

    override suspend fun getCharacterDetail(charId: String): Response<NetworkCharacter> = request {
        val result: NetworkCharacter = client.get {
            url {
                path("/api/character/${charId}")
            }

        }.body()

        return@request Response.Success(result)
    }
}