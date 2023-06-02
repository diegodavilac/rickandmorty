package dev.diegodc.data.network.helper

import dev.diegodc.data.network.RickAndMortyApi
import dev.diegodc.data.network.models.NetworkCharacter
import dev.diegodc.data.network.models.NetworkResponse
import dev.diegodc.data.network.utils.Response
import dev.diegodc.data.network.utils.request
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RickAndMortyMockImpl(engine: HttpClientEngine) : RickAndMortyApi{

    private val client by lazy { HttpClient(engine){
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                    ignoreUnknownKeys = true
                }
            )
        }
    } }

    override suspend fun getCharacters(page: Int,params: Map<String, String?>): Response<List<NetworkCharacter>> = request {
        val result : NetworkResponse<List<NetworkCharacter>> = client.get{
            url {
                path("/api/character")
                parameters.append("page", page.toString())
            }

        }.body()

        return@request Response.Success(result.results)
    }

    override suspend fun getCharacterDetail(charId: String): Response<NetworkCharacter> {
        TODO("Not yet implemented")
    }
}