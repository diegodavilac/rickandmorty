package dev.diegodc.data.network.api

import dev.diegodc.data.network.helper.RickAndMortyMockImpl
import dev.diegodc.data.network.models.NetworkCharacter
import dev.diegodc.data.network.models.NetworkResponse
import dev.diegodc.data.network.utils.Response
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class RickAndMortyApiImplTest {

    companion object {
        const val jsonCharacter = """
           {
	"info": {
		"count": 826,
		"pages": 42,
		"next": "https://rickandmortyapi.com/api/character?page=2",
		"prev": null
	},
	"results": [{
		"id": 1,
		"name": "Rick Sanchez",
		"status": "Alive",
		"species": "Human",
		"type": "",
		"gender": "Male",
		"origin": {
			"name": "Earth (C-137)",
			"url": "https://rickandmortyapi.com/api/location/1"
		},
		"location": {
			"name": "Citadel of Ricks",
			"url": "https://rickandmortyapi.com/api/location/3"
		},
		"image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
		"episode": ["https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2", "https://rickandmortyapi.com/api/episode/3", "https://rickandmortyapi.com/api/episode/4", "https://rickandmortyapi.com/api/episode/5", "https://rickandmortyapi.com/api/episode/6", "https://rickandmortyapi.com/api/episode/7", "https://rickandmortyapi.com/api/episode/8", "https://rickandmortyapi.com/api/episode/9", "https://rickandmortyapi.com/api/episode/10", "https://rickandmortyapi.com/api/episode/11", "https://rickandmortyapi.com/api/episode/12", "https://rickandmortyapi.com/api/episode/13", "https://rickandmortyapi.com/api/episode/14", "https://rickandmortyapi.com/api/episode/15", "https://rickandmortyapi.com/api/episode/16", "https://rickandmortyapi.com/api/episode/17", "https://rickandmortyapi.com/api/episode/18", "https://rickandmortyapi.com/api/episode/19", "https://rickandmortyapi.com/api/episode/20", "https://rickandmortyapi.com/api/episode/21", "https://rickandmortyapi.com/api/episode/22", "https://rickandmortyapi.com/api/episode/23", "https://rickandmortyapi.com/api/episode/24", "https://rickandmortyapi.com/api/episode/25", "https://rickandmortyapi.com/api/episode/26", "https://rickandmortyapi.com/api/episode/27", "https://rickandmortyapi.com/api/episode/28", "https://rickandmortyapi.com/api/episode/29", "https://rickandmortyapi.com/api/episode/30", "https://rickandmortyapi.com/api/episode/31", "https://rickandmortyapi.com/api/episode/32", "https://rickandmortyapi.com/api/episode/33", "https://rickandmortyapi.com/api/episode/34", "https://rickandmortyapi.com/api/episode/35", "https://rickandmortyapi.com/api/episode/36", "https://rickandmortyapi.com/api/episode/37", "https://rickandmortyapi.com/api/episode/38", "https://rickandmortyapi.com/api/episode/39", "https://rickandmortyapi.com/api/episode/40", "https://rickandmortyapi.com/api/episode/41", "https://rickandmortyapi.com/api/episode/42", "https://rickandmortyapi.com/api/episode/43", "https://rickandmortyapi.com/api/episode/44", "https://rickandmortyapi.com/api/episode/45", "https://rickandmortyapi.com/api/episode/46", "https://rickandmortyapi.com/api/episode/47", "https://rickandmortyapi.com/api/episode/48", "https://rickandmortyapi.com/api/episode/49", "https://rickandmortyapi.com/api/episode/50", "https://rickandmortyapi.com/api/episode/51"],
		"url": "https://rickandmortyapi.com/api/character/1",
		"created": "2017-11-04T18:48:46.250Z"
	}]
}
        """
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }
    private lateinit var mockEngine: MockEngine

    @Before
    fun setup() {
        mockEngine = MockEngine { request ->
            respond(
                content = jsonCharacter,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
    }

    @Test
    fun `getCharacters happy path`() {
        // Arrange
        val api = RickAndMortyMockImpl(mockEngine)
        val expectedCharacters: NetworkResponse<List<NetworkCharacter>> = json.decodeFromString(
            NetworkResponse.serializer(ListSerializer(NetworkCharacter.serializer())),
            jsonCharacter
        )

        runBlocking {
            // Act
            val actualCharacters = api.getCharacters(1)

            // Assert
            assertThat((actualCharacters as Response.Success).data).isEqualTo(expectedCharacters.results)
        }

    }
}