package dev.diegodc.features.characters.characterDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.diegodc.core.common.base.BaseViewModel
import dev.diegodc.core.common.result.Result
import dev.diegodc.core.common.result.asResult
import dev.diegodc.data.repository.interfaces.ICharacterRepository
import dev.diegodc.features.characters.navigation.charactersArgId
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: ICharacterRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CharacterDetailViewState>(CharacterDetailViewState()) {

    private val charId: String = savedStateHandle[charactersArgId] ?: ""

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            characterRepository.getCharacter(charId).asResult().collect {
                when (it) {
                    is Result.Error -> TODO()
                    is Result.Loading -> updateState {
                        copy(isLoading = true)
                    }

                    is Result.Success -> updateState {
                        copy(
                            isLoading = false,
                            detail = it.data
                        )
                    }
                }
            }
        }
    }
}