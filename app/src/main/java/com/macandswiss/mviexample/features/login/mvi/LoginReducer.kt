package com.macandswiss.mviexample.login.mvi

import com.macandswiss.mviexample.mvi.Reducer

/**
 * Actually takes the state of the login along with an action to produce a new login state
 *
 * This reducer would also be the location where you could apply side effects (i.e. navigation)
 */
class LoginReducer : Reducer<LoginState, LoginAction> {
    override fun reduce(currentState: LoginState, action: LoginAction): LoginState {
        return when(action) {
            is LoginAction.PasswordChanged -> {
                currentState.copy(password = action.newPassword)
            }
            is LoginAction.LoginChanged -> {
                currentState.copy(login = action.newLogin)
            }
            is LoginAction.LoginStarted -> {
                currentState.copy(progressBar = true)
            }
            is LoginAction.LoginCompleted -> {
                currentState.copy(progressBar = false)
            }
            is LoginAction.LoginError -> {
                currentState.copy(errorMessage = action.errorMessage.toString())
            }
            else -> {
                //Even though this is redundant at the moment, it is useful to have this in place in case we add new actions
                //TODO: Additional error handling
                currentState
            }
        }
    }
}