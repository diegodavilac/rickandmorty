package dev.diegodc.data.repository

import dev.diegodc.core.common.exceptions.NetworkException
import dev.diegodc.data.network.RickAndMortyApi
import dev.diegodc.data.network.utils.Response
import dev.diegodc.data.preferences.CommonPreferences
import dev.diegodc.data.repository.interfaces.ICharacterRepository
import dev.diegodc.data.repository.mapper.toDomain
import dev.diegodc.data.repository.model.RickAndMortyCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: RickAndMortyApi,
    private val commonPreferences: CommonPreferences
) : ICharacterRepository {

    override suspend fun getCharacters(page: Int): Flow<List<RickAndMortyCharacter>> = flow {
        val filters = commonPreferences.getFilters()
        val params : MutableMap<String, String?> = mutableMapOf()

        params["name"] = filters.name
        params["gender"] = filters.gender?.name
        params["species"] = filters.species?.name
        params["status"] = filters.status?.name

        //Delay added to simulate wait time and show animation
        kotlinx.coroutines.delay(1500L)
        emit(api.getCharacters(page, params))
    }.map {
        when (it) {
            is Response.Error -> throw NetworkException(
                message = it.error,
                code = it.code ?: 0
            )

            is Response.Success -> it.data.map { character -> character.toDomain() }
        }
    }

    override suspend fun getCharacter(characterId: String): Flow<RickAndMortyCharacter> = flow {
        emit(api.getCharacterDetail(characterId))
    }.map {
        when (it) {
            is Response.Error -> throw NetworkException(
                message = it.error,
                code = it.code ?: 0
            )

            is Response.Success -> it.data.toDomain()
        }
    }
}