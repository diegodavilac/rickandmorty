package dev.diegodc.features.characters.root

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.diegodc.core.common.base.BaseViewModel
import dev.diegodc.core.common.result.Result
import dev.diegodc.core.common.result.asResult
import dev.diegodc.data.repository.interfaces.ICharacterRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: ICharacterRepository
) : BaseViewModel<CharactersViewState>(CharactersViewState()) {

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            characterRepository.getCharacters(state.value.page)
                .asResult()
                .collect {
                    when (it) {
                        is Result.Error -> {
                            Log.e("CharacterViewModel", it.toString())
                        }
                        is Result.Loading -> {
                            updateState {
                                copy(isLoading = true)
                            }
                        }

                        is Result.Success -> {
                            updateState {
                                copy(isLoading = false, items = it.data)
                            }
                        }
                    }
                }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            characterRepository.getCharacters(state.value.page + 1)
                .asResult()
                .collect { result ->
                    when (result) {
                        is Result.Error -> TODO()
                        is Result.Loading -> {
                            updateState {
                                copy(isLoadingMore = true)
                            }
                        }

                        is Result.Success -> {
                            updateState {
                                copy(
                                    isLoadingMore = false,
                                    items = items.toMutableList().apply {
                                        addAll(result.data)
                                    },
                                    page = page+1
                                )
                            }
                        }
                    }
                }
        }
    }
}