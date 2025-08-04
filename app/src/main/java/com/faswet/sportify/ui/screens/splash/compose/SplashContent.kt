package com.faswet.sportify.ui.screens.splash.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.screens.splash.contract.SplashContract
import com.faswet.sportify.ui.theme.dimens

@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    state: SplashContract.State,
    onEventSent: (event: SplashContract.Event) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.ime, modifier = modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize(),
        bottomBar = {
        }) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_sportify_logo),
                    contentDescription = "",
                    modifier.size(MaterialTheme.dimens.size200dp)
                )
            }
        }
    }
}