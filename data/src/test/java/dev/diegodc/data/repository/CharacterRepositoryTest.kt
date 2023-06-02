package dev.diegodc.data.repository

import dev.diegodc.core.common.exceptions.NetworkException
import dev.diegodc.data.network.RickAndMortyApi
import dev.diegodc.data.network.models.NetworkCharacter
import dev.diegodc.data.network.utils.Response
import dev.diegodc.data.repository.mapper.toDomain
import dev.diegodc.data.repository.model.RickAndMortyCharacter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows


class CharacterRepositoryTest {

    companion object {
        const val jsonCharacter = """
            {
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
	}
        """
    }

    private val json = Json

    @Test
    fun `getCharacters happy path`() {
        // Arrange
        val api = mockk<RickAndMortyApi>()
        val repository = CharacterRepository(api)
        val expectedCharacters = listOf(
            json.decodeFromString(NetworkCharacter.serializer(), jsonCharacter)
        )
        coEvery { api.getCharacters(any()) } returns Response.Success(expectedCharacters)

        runBlocking {
            //Act
            val mappedExpectedCharacters = expectedCharacters.map { it.toDomain() }
            repository.getCharacters(1).collect{actualCharacters ->
                // Assert
                assertThat(actualCharacters).isEqualTo(mappedExpectedCharacters)
            }

        }
    }

    @Test
    fun `getCharacters unhappy path - network exception`() {
        // Arrange
        val api = mockk<RickAndMortyApi>()
        val repository = CharacterRepository(api)
        coEvery { api.getCharacters(any()) } throws NetworkException("Error message", 500)

        var exception : NetworkException? = null
        runBlocking {
            // Act
            repository.getCharacters(1)
                .catch {
                    exception = assertThrows<NetworkException> {
                        throw it
                    }
                }
                .collect{}


            // Assert
            assertThat(exception?.message).isEqualTo("Error message")
            assertThat(exception?.code).isEqualTo(500)
        }
    }
}