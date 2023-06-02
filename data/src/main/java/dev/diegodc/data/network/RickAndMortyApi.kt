package dev.diegodc.data.network

import dev.diegodc.data.network.models.NetworkCharacter
import dev.diegodc.data.network.utils.Response

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int, params: Map<String, String?>): Response<List<NetworkCharacter>>
    suspend fun getCharacterDetail(charId: String) : Response<NetworkCharacter>
}