package com.uvg.javier.Screen1

import LoginRoute
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder


@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
){
    composable<LoginDestination>{
        LoginRoute(
            onLoginClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()

        )
    }
}