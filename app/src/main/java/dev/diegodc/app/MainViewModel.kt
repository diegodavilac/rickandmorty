package dev.diegodc.app

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.diegodc.core.common.base.BaseViewModel
import dev.diegodc.data.preferences.CommonPreferences
import dev.diegodc.data.preferences.model.Filters
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val commonPreferences: CommonPreferences
): BaseViewModel<MainState>(MainState()) {
    init {
       refresh()
    }

    fun refresh(){
        updateState {
            copy(hasFilters = commonPreferences.getFilters() != Filters())
        }
    }
}

data class MainState(
    val hasFilters: Boolean = false
)