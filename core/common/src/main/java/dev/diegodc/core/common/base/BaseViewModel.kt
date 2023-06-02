package dev.diegodc.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<ScreenState : Any>(
    initialState: ScreenState,
) : ViewModel() {

    @PublishedApi
    internal val mState: MutableStateFlow<ScreenState> = MutableStateFlow(initialState)
    val state: StateFlow<ScreenState> = mState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = initialState,
    )

    inline fun updateState(crossinline function: ScreenState.() -> ScreenState) {
        mState.update(function)
    }
}