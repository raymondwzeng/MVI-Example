package com.macandswiss.mviexample.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A container of state for a given screen.
 *
 * We want the screen to start at a given state, and use its specific reducer to navigate around its own "FSM".
 */
class Store(
    initialState: State,
    private val reducer: Reducer
) {
    private val _currentState = MutableStateFlow(initialState)
    val state : StateFlow<State> = _currentState //Exposes current state to any class that uses the store

    fun dispatch(action: Action) {
        //We could use some middleware here to log or apply other effects.
        _currentState.value = reducer.reduce(_currentState.value, action)
    }
}