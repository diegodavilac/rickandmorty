package dev.diegodc.features.characters.root

import dev.diegodc.data.repository.model.RickAndMortyCharacter

data class CharactersViewState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
    val items: List<RickAndMortyCharacter> = emptyList()
)