package dev.diegodc.data.repository.mapper

import dev.diegodc.data.network.models.NetworkCharacter
import dev.diegodc.data.repository.model.RickAndMortyCharacter

fun NetworkCharacter.toDomain() : RickAndMortyCharacter{
    return RickAndMortyCharacter(
        id, name, image
    )
}