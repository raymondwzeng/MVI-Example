package com.macandswiss.mviexample.features.login.mvi

import com.macandswiss.mviexample.mvi.State

/**
 * The state or model representing the loginUI, to be passed to the view.
 *
 * This could also be called the "model" to some developers.
 */
data class LoginState(
    val login: String = "",
    val password: String = "",
    val progressBar: Boolean = false,
    val errorMessage : String? = null
) : State
