package com.macandswiss.mviexample.login.mvi

import com.macandswiss.mviexample.mvi.Action

/**
 * Represents the actions relating to logging in, such as regular login, changing password, and signup
 */
sealed class LoginAction : Action {
    data class LoginChanged(val newLogin: String) : LoginAction()
    data class PasswordChanged(val newPassword: String) : LoginAction()
    object LoginStarted : LoginAction()
    object LoginCompleted : LoginAction()
    data class LoginError(val errorMessage: Throwable?) :LoginAction()
}
