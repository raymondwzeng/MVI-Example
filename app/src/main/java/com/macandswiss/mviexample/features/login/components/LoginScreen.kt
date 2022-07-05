package com.macandswiss.mviexample.features.login.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.macandswiss.mviexample.features.login.mvi.LoginState
import com.macandswiss.mviexample.ui.theme.MVIExampleTheme
import kotlinx.coroutines.flow.collect

/**
 * The actual screen of the login feature. Takes a view model and renders based on its state
 *
 * Question: Is this normally a fragment? An activity?
 * Well, let's think about it this way - will a login be a standalone entity, or could it be part of a greater activity?
 * I think for now it makes sense to have it as a standalone
 */
class LoginScreen : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Create a new instance or get the instance of the viewmodel(?) and assign its lifecycle owner to this
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        lifecycleScope.launchWhenCreated {
            viewModel.currentState.collect { state: LoginState ->
                setContent {
                    Login(
                        state = state,
                        onLoginValueChanged = { viewModel.LoginChanged(newLogin = it) },
                        onPasswordValueChanged = { viewModel.PasswordChanged(newPassword = it) },
                        onSignInPressed = { viewModel.LoginStarted() }
                    )
                }
            }
        }
    }
}

@Composable
private fun Login(
    state: LoginState,
    onLoginValueChanged: (String) -> Unit = {},
    onPasswordValueChanged: (String) -> Unit = {},
    onSignInPressed: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        if (state.progressBar) {
            CircularProgressIndicator(modifier = modifier)
        } else {
            Text(
                text = "Login",
                style = MaterialTheme.typography.h4
            )
            TextField(
                value = state.login,
                onValueChange = onLoginValueChanged,
                maxLines = 1
            )
            Text(
                text = "Password",
                style = MaterialTheme.typography.h4
            )
            TextField(
                value = state.password,
                onValueChange = onPasswordValueChanged,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                maxLines = 1
            )
            Divider(modifier = Modifier.size(16.dp), color = Color.Transparent)
            Button(onClick = onSignInPressed) {
                Text(text = "Log In")
            }
        }
    }
}

@Composable
@Preview
fun PreviewLoginStandard() {
    MVIExampleTheme {
        Login(LoginState())
    }
}