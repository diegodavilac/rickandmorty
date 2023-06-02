package dev.diegodc.features.characters.characterDetail

import dev.diegodc.data.repository.model.RickAndMortyCharacter

data class CharacterDetailViewState(
    val isLoading: Boolean = false,
    val detail: RickAndMortyCharacter? = null,
)
