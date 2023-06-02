package dev.diegodc.features.characters.filters

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.diegodc.core.common.base.BaseViewModel
import dev.diegodc.core.common.utils.asEnumOrNull
import dev.diegodc.data.preferences.CommonPreferences
import dev.diegodc.features.characters.filters.model.toData
import dev.diegodc.features.characters.filters.model.toUi
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val commonPreferences: CommonPreferences
) : BaseViewModel<FiltersViewState>(FiltersViewState()) {

    init {
        getFilters()
    }

    fun clearFilters(){
        updateState { FiltersViewState() }
        commonPreferences.clearFilters()
    }

    fun updateName(name: String) {
        updateState {
            copy(name = name)
        }
    }

    fun updateStatus(value: String) {
        updateState {
            copy(status = value.asEnumOrNull<CharacterStatus>(null))
        }
    }

    fun updateGender(gender: String) {
        updateState {
            copy(gender = gender.asEnumOrNull<CharacterGender>(null))
        }
    }

    fun updateSpecies(species: String) {
        updateState {
            copy(species = species.asEnumOrNull<CharacterSpecies>(null))
        }
    }

    fun sendFilters(){
        commonPreferences.saveFilters(state.value.toData())
    }

    private fun getFilters(){
        commonPreferences.getFilters().toUi().let{
            updateState { it }
        }
    }
}