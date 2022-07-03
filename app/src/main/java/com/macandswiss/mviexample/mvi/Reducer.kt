package com.macandswiss.mviexample.mvi

/**
 * State + Action = New State :) [Think FSMs]
 *
 * Don't you just love immutability?
 *
 * We could also use generics here @ declaration to guarantee that a certain state/action pair is used
 */
interface Reducer {
    fun reduce(currentState: State, action: Action) : State
}