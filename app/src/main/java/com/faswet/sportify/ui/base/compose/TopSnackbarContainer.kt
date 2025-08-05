package com.faswet.sportify.ui.base.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.faswet.sportify.ui.theme.dimens

@Composable
fun TopSnackBarContainer(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackBarColor: Color,
    snackBarTextColor: Color,
    snackBarActionTextColor: Color,
    snackBarActionColor: Color,
    content: @Composable BoxScope.() -> Unit,
    ) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        content()
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.padding(MaterialTheme.dimens.size8dp),
            snackbar = { snackBarData ->
                Snackbar(
                    containerColor = snackBarColor,
                    contentColor = snackBarTextColor,
                    actionColor = snackBarActionTextColor,
                    actionContentColor = snackBarActionColor,
                    snackbarData = snackBarData,
                )
            }
        )
    }
}