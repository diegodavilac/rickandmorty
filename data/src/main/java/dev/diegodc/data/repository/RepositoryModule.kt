package dev.diegodc.data.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.diegodc.data.network.RickAndMortyApi
import dev.diegodc.data.repository.interfaces.ICharacterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickAndMortyApi): ICharacterRepository {
        return CharacterRepository(api)
    }
}