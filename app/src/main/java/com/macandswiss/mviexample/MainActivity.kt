package com.macandswiss.mviexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.macandswiss.mviexample.features.login.components.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginScreen::class.java))
    }
}