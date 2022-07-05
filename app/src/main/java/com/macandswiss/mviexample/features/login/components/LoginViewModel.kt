package com.macandswiss.mviexample.features.login.components

import androidx.lifecycle.ViewModel
import com.macandswiss.mviexample.features.login.mvi.LoginAction
import com.macandswiss.mviexample.features.login.mvi.LoginReducer
import com.macandswiss.mviexample.features.login.mvi.LoginState
import com.macandswiss.mviexample.mvi.Store

class LoginViewModel : ViewModel() {
    //The "CPU" for our data cycle, delegating state management to something which isn't the VM.
    private val _store = Store(
        initialState = LoginState(),
        reducer = LoginReducer()
    )

    //Exposed so that the View knows what to do
    val currentState = _store.state

    //Proxy functions that end up calling the store
    fun LoginChanged(newLogin: String) {
        _store.dispatch(LoginAction.LoginChanged(newLogin))
    }

    fun PasswordChanged(newPassword: String) {
        _store.dispatch(LoginAction.PasswordChanged(newPassword))
    }

    fun LoginStarted() {
        _store.dispatch(LoginAction.LoginStarted)
    }

    //We don't need to create proxies for LoginCompleted and LoginErrored because we do not want the user to perform these actions
}