package dev.diegodc.data.repository.interfaces

import dev.diegodc.data.repository.model.RickAndMortyCharacter
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
   suspend fun getCharacters(page: Int): Flow<List<RickAndMortyCharacter>>
   suspend fun getCharacter(characterId: String): Flow<RickAndMortyCharacter>
}